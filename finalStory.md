//TODO: 5.0
## **Epic: Rewards Points Management**

> Enable automatic reward point calculation, persistence, exposure via APIs, and validation through tests.

---

## **User Story 1: RewardsPoint Domain Modeling**

**As a** backend developer
**I want** to model rewards points as a persistent domain entity
**So that** reward balances can be stored per wallet.

### Acceptance Criteria

* `RewardsPoint` JPA entity is created
* Entity is mapped to `rewards_point` table
* Fields include:

    * id
    * points
    * wallet (many-to-one, mandatory)
    * updatedAt
* Table is auto-created via JPA DDL
* `updatedAt` is automatically updated using JPA lifecycle callbacks
* One rewards record exists per wallet

---

## **User Story 2: Rewards Persistence & Repository**

**As a** backend developer
**I want** a repository to manage reward point persistence
**So that** rewards can be reliably queried and updated.

### Acceptance Criteria

* `RewardsPointRepository` extends `JpaRepository`
* Custom finder exists:

    * `findByWalletId(Long walletId)`
* Repository enforces:

    * Single rewards record per wallet
    * Default points = 0 for new wallets
* Repository is unit tested using `@DataJpaTest`
* Tests validate:

    * Table creation
    * Wallet-based lookup

---

## **User Story 3: Rewards Calculation Business Logic**

**As a** system
**I want** to calculate and accumulate reward points during transactions
**So that** users earn rewards for spending.

### Acceptance Criteria

* Rewards logic resides **only in Service layer**
* Logic is triggered after transaction persistence
* Rules:

    * Only **DEBIT** transactions generate rewards
    * 1 reward point per 100 units spent
    * CREDIT transactions generate zero rewards
* Rewards are accumulated (never overwritten)
* Logic is idempotent per transaction
* Failure in rewards logic does not break transaction flow

---

## **User Story 4: Rewards REST Controller API**

**As a** frontend or API consumer
**I want** REST APIs to fetch reward points
**So that** users can view their earned rewards.

### Acceptance Criteria

* `RewardsPointController` is created
* Controller is annotated with:

    * `@RestController`
    * `@RequestMapping("/api/rewards")`
* Controller contains **no business logic**
* Controller delegates to `RewardsPointService`

#### API Endpoint

```
GET /api/rewards/wallet/{walletId}
```

* Returns:

    * `200 OK` with rewards JSON if found
    * `404 NOT FOUND` if rewards do not exist
* Proper HTTP status codes are used

---

## **User Story 5: Testing & End-to-End Validation**

**As a** developer / QA
**I want** full unit and flow validation
**So that** the feature is production-ready.

### Acceptance Criteria

* Service layer is unit tested using Mockito
* Test cases include:

    * No rewards for CREDIT transaction
    * Correct reward calculation for DEBIT transaction
    * Accumulation across multiple transactions
* Tests follow GIVEN–WHEN–THEN format
* End-to-end flow validation:

    * Wallet creation
    * DEBIT transaction creation
    * Rewards accumulation
    * Rewards fetched via API
* Database verification confirms:

    * `rewards_point` table exists
    * Foreign key constraint on wallet
    * `updatedAt` auto-updates



