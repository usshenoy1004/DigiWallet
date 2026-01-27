
1. UserService TODOs (1.x)

1.1 - Implement `UserService.getUserById(Long id)`
- File: `src/main/java/com/orion/DigiWallet/service/UserService.java`
- Snippet:
  //TODO: 1.1
  public User getUserById(Long id) {
    // Log incoming request with user ID
    // Fetch user from repository
    // test the result on swagger or postman
    return null;
  }
- Expectation / Acceptance Criteria:
  - Method fetches the user by `id` via `userRepository.findById(id)`.
  - If user not found, throw `RuntimeException("User not found with id: " + id)` (or a custom NotFound exception if preferred).
  - Before returning, call `generateGreetingMsg(user.getRole())` and set the greeting on the `User` object via `user.setUserGreetingMessage(...)`.
  - Log the incoming request at info level (e.g., `logger.info("Fetching user with id {}", id);`).
  - Unit tests that expect an existing user should pass.
- Dependencies: `UserRepository`, `generateGreetingMsg` method.
- Priority: High — used by controllers and tests.
- Next steps:
  1. Implement repository lookup with Optional handling.
  2. Call `generateGreetingMsg(...)` and set greeting.
  3. Add logging and update unit tests (un-disable where applicable).

1.2 - Implement `generateGreetingMsg(String role)`
- File: `src/main/java/com/orion/DigiWallet/service/UserService.java`
- Snippet:
  String generateGreetingMsg(String role) {
    //TODO: 1.2
    // Perform a case-insensitive check to determine the role.
  }
- Expectation / Acceptance Criteria:
  - Implement case-insensitive logic: if role contains "ADMIN" (case-insensitive), return e.g., "Admin access granted" or a specified admin greeting; otherwise return "User access granted".
  - If role is null or empty, return a default user greeting (e.g., "User access").
  - Unit tests should cover Admin/Non-Admin/Null cases.
- Dependencies: none (pure function used by services).
- Priority: High — used by getUserById and getAllUsers tests.
- Next steps:
  1. Implement role checks with `String.equalsIgnoreCase` or `toUpperCase()`.
  2. Add unit tests or enable the ones in `UserServiceTest`.

1.3 - Logging & repository call inside `getUserById` (part of 1.1)
- File: `src/main/java/com/orion/DigiWallet/service/UserService.java`
- Snippet: See 1.1
- Expectation: Add `logger.info("Fetching user with id {}", id);` before repository fetch. Ensure errors are logged at warn or error levels.
- Priority: Medium
- Next steps: Add logging statements in the method implementation.

1.4 - Populate greeting messages in `getAllUsers()`
- File: `src/main/java/com/orion/DigiWallet/service/UserService.java`
- Snippet:
  public List<User> getAllUsers() {
    logger.info("Fetching all users from database");
    List<User> users = userRepository.findAll();
    logger.info("Total users fetched: {}", users.size());
    return users;
    //TODO: 1.4
    // For each user in the list, call generateGreetingMsg(user)
  }
- Expectation / Acceptance Criteria:
  - For each `User` in `users`, call `generateGreetingMsg(user.getRole())` and set the result on the user (`user.setUserGreetingMessage(...)`) before returning the list.
  - Unit tests that verify greeting presence should pass after this.
- Dependencies: `generateGreetingMsg`.
- Priority: Medium
- Next steps:
  1. Iterate users list and set greeting messages.
  2. Re-run `UserServiceTest` and enable assertions (1.8.1 etc.).

1.6 / 1.7 / 1.8 / 1.9 / 1.10 - Unit Test TODOs in `UserServiceTest` (tests are partly disabled)
- File: `src/test/java/com/orion/DigiWallet/service/UserServiceTest.java`
- Snippets include several disabled tests annotated `@Disabled` and TODO markers such as `//TODO: 1.9` and `//TODO: 1.10`.
- Expectation / Acceptance Criteria:
  - Implement the unit tests described by the TODOs (e.g., `getUserById_shouldReturnUserWithGreetingMessage`, tests for `generateGreetingMsg` covering Admin/User/Null, tests for `createUser` etc.).
  - Remove `@Disabled` only after the corresponding service methods are implemented.
- Source marker: Some tests are explicitly `@Disabled` — mark these as "Source: Disabled test" in task tracking.
- Priority: Medium
- Next steps:
  1. Implement service logic (1.1–1.4, 1.2) first.
  2. Remove `@Disabled` and implement test bodies where TODOs show placeholder comments.

===============================================================================

2. CardService & CardRepository TODOs (2.x / 3.x cross refs)

2.1.0 - Annotate `CardService` as `@Service` (already present)
- File: `src/main/java/com/orion/DigiWallet/service/CardService.java`
- Snippet:
  //TODO: 2.1.0
  // annotate the class to be a service component
  @Service
  public class CardService {
- Expectation: Class is annotated with `@Service` (already done). No action required unless additional configuration is desired.
- Priority: Low
- Next steps: Confirm `@Service` is present and proceed to implement methods below.

2.1.1 - 2.1.3 - Inject `CardRepository`
- File: `src/main/java/com/orion/DigiWallet/service/CardService.java`
- Snippet:
  //TODO: 2.1.1
  // create a private final field for CardRepository (dependency)
  //TODO: 2.1.2
  // create a constructor to inject CardRepository
- Expectation / Acceptance Criteria:
  - Add `private final CardRepository cardRepository;` to the class.
  - Add a constructor `public CardService(CardRepository cardRepository) { this.cardRepository = cardRepository; }` annotated with `@Autowired` optional (constructor injection works without it).
  - The field should be final for immutability and testability.
- Dependencies: `CardRepository`.
- Priority: High
- Next steps:
  1. Add the final field and constructor.
  2. Update or create unit tests to inject a mock repository.

2.1.3 - 2.1.4 - Implement `createCard(Card)` and `getCardById(Long)`
- File: `src/main/java/com/orion/DigiWallet/service/CardService.java`
- Snippet:
  public Card createCard(Card card) {
    // STEP 1: Check if card number already exists
    // throw runtime exception if it exists "Card number already exists"
    // STEP 2: Save and return the card
    return null;
  }
  //TODO: 2.1.4
  // -----------------------------------------
  // GET CARD BY ID
- Expectation / Acceptance Criteria for `createCard`:
  - Check `cardRepository.existsByCardNumber(card.getCardNumber())` and if true throw `RuntimeException("Card number already exists")`.
  - Save via `cardRepository.save(card)` and return saved entity.
  - Add `@Transactional` if required.
- Expectation for `getCardById`:
  - Return card found by `cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found with id: " + id));`.
  - Unit tests for happy & not-found paths.
- Dependencies: `CardRepository`.
- Priority: High
- Next steps:
  1. Implement exists check, save, and fetch with appropriate exceptions.
  2. Add/enable service tests and controller tests.

2.1.5 - 2.1.6 - Implement `updateCard` and `deleteCard`
- File: `src/main/java/com/orion/DigiWallet/service/CardService.java`
- Snippet:
  public Card updateCard(Long id, Card updatedCard) {
    // STEP 1: Fetch existing card
    // throw runtime exception if not found "Card not found with id: " + id
    // STEP 2: Update allowed fields
    // ... assume all fields except id and cardNumber can be updated
    // STEP 3: Save updated card
    return null;
  }
  public void deleteCard(Long id) {
    // STEP 1: Check if card exists
- Expectation / Acceptance Criteria:
  - `updateCard`: fetch, update allowed fields (e.g., holderName, expiry, status), save, return saved entity. Do not allow modification of `id` or `cardNumber`.
  - `deleteCard`: if exists, delete by id; if not exists, throw `RuntimeException("Card not found with id: " + id)`.
- Dependencies: `CardRepository`.
- Priority: High
- Next steps:
  1. Implement fetch-or-throw, field updates, and save.
  2. Add unit tests and controller tests.

2.2 - CardRepository annotation and method checks
- File: `src/main/java/com/orion/DigiWallet/repository/CardRepository.java`
- Snippet:
  //TODO: 2.2
  //make this class a repository for Card entity use annotation
  //make this interface extend JpaRepository with Card as entity and Long as ID type
  @Repository
  public interface CardRepository extends JpaRepository<Card, Long> {
    // TODO: 3.1
    // Write a method to find a card by cardNumber
    Optional<Card> findByCardNumber(String cardNumber);
    // TODO: 3.2
    // Write a method to check if a card exists by cardNumber
    boolean existsByCardNumber(String cardNumber);
  }
- Expectation: The repository is already annotated and extends `JpaRepository`. Ensure `findByCardNumber` and `existsByCardNumber` signatures are correct — they exist already.
- Priority: Low
- Next steps: No change required; verify repository tests that rely on these methods.

===============================================================================

3. Models & Repository Tests (3.x)

3.2 - `Category` entity fields and getters/setters
- File: `src/main/java/com/orion/DigiWallet/model/Category.java`
- Snippet:
  //TODO: 3.2
  // Define this class as a JPA entity mapped to "category" table
  @Entity
  @Table(name = "category")
  public class Category {
    //TODO: 3.2.1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //TODO: 3.2.2
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    //TODO: 3.2.3
    @Column(name = "type", nullable = false, length = 20)
    private String type;
    //TODO: 3.2.4
    // Generate getters and setters for all fields
- Expectation / Acceptance Criteria:
  - Ensure `Category` is annotated with `@Entity` and `@Table(name = "category")` (already present).
  - Ensure getters and setters for `id`, `name`, and `type` are implemented and public.
  - Validate with repository tests that saving and reading Category works.
- Priority: Medium
- Next steps:
  1. Confirm or add missing getters/setters.
  2. Run `TransactionRepositoryTest` which depends on `Category`.

3.3 - `RewardsPoint` entity creation
- File: `src/main/java/com/orion/DigiWallet/model/RewardsPoint.java`
- Snippet:
  //TODO: 3.3
  // Define this class as a JPA entity mapped to "rewards_point" table
  public class RewardsPoint {
    //TODO: 3.3.1
    // Define id as primary key with auto-generation strategy
    //TODO: 3.3.2
    // Define points field to store the number of reward points
    //TODO: 3.3.3
    // Define wallet field to establish many-to-one relationship with Wallet entity
    // Use @JoinColumn with name "wallet_id" and make it NOT NULL
    //TODO: 3.3.4
    // Define updatedAt field to store the timestamp of last update
    //TODO: 3.3.5
    // Generate getters and setters for all fields
- Expectation / Acceptance Criteria:
  - Implement `@Entity` and `@Table(name = "rewards_point")`.
  - Fields: `id` (auto-generated), `points` (integer or long, default 0), `wallet` (ManyToOne, `@JoinColumn(name = "wallet_id", nullable = false)`), `updatedAt` (timestamp auto-updated via `@PrePersist`/`@PreUpdate` or Hibernate `@UpdateTimestamp`).
  - Add `RewardsPointRepository` extending `JpaRepository<RewardsPoint, Long>` and write `findByWalletId(Long walletId)`.
  - Tests: add `@DataJpaTest` to verify table creation and repository queries.
- Dependencies: `Wallet` entity, DB schema.
- Priority: High (feature epic per `finalStory.md`).
- Next steps:
  1. Create the entity with correct annotations.
  2. Create repository and unit tests.

3.5 - `CardRepositoryTest` setup & tests
- File: `src/test/java/com/orion/DigiWallet/repository/CardRepositoryTest.java`
- Snippet:
  //TODO: 3.5.2: REVIEW SETUP METHOD
  @BeforeEach
  void setUp() {
    // create and save User
    // create and save Wallet
  }
  //TODO: 3.5.3: Write test to verify: A Card can be saved successfully
  @Disabled
  @Test
  void shouldSaveCardSuccessfully() { ... }
- Expectation / Acceptance Criteria:
  - Ensure `@BeforeEach` setup correctly creates and persists a `User` and `Wallet` required by `Card` tests.
  - Implement disabled tests: saving card, fetching by card number, id generation checks. Remove `@Disabled` after implementation.
- Priority: Medium
- Next steps:
  1. Verify repositories are injected via `@Autowired` or test configuration.
  2. Implement the test bodies and run `mvn -Dtest=CardRepositoryTest test`.

3.6 - `TransactionRepositoryTest` setup & tests (3.6.1..3.6.7)
- File: `src/test/java/com/orion/DigiWallet/repository/TransactionRepositoryTest.java`
- Snippet:
  //TODO: 3.6.2: SET OTHER MANDATORY FIELDS IF ANY IN USER ENTITY
  //TODO: 3.6.3: Create wallet with dummy data eg balance, currency, status etc
  //TODO: 3.6.4: Create category with type "EXPENSE"
  //TODO: 3.6.5: READ ONLY (disabled tests for saving transactions)
- Expectation / Acceptance Criteria:
  - Complete `@BeforeEach` to persist a `User`, `Wallet` (balance BigDecimal.valueOf(1000), currency "INR", status "ACTIVE"), and a `Category` (type "EXPENSE").
  - Implement and enable tests: save Transaction, verify ID generated, find transactions by user ID, verify empty list case.
- Priority: Medium
- Next steps:
  1. Implement setup with valid entities.
  2. Remove `@Disabled` after verifying repository methods work.

3.7 - `WalletRepositoryTest` (enable tests)
- File: `src/test/java/com/orion/DigiWallet/repository/WalletRepositoryTest.java`
- Snippet: `//TODO: 3.7: remove @Disabled to enable the tests`
- Expectation / Acceptance Criteria:
  - Remove `@Disabled` after `Wallet` service/repository methods are implemented. Ensure tests pass.
- Priority: Medium
- Next steps:
  1. Implement or verify `WalletRepository` behavior; enable tests and run.

===============================================================================

4. Controllers & Controller Tests (4.x)

4.0 - `TransactionController` checks
- File: `src/main/java/com/orion/DigiWallet/controller/TransactionController.java`
- Snippet: `//TODO: 4.0.1` and `//TODO: 4.0.2` present in controller file
- Expectation / Acceptance Criteria:
  - Review methods in `TransactionController` for completeness, correct request mappings, logging, and HTTP statuses. Ensure they delegate to `TransactionService` and do not contain business logic.
  - Unit tests or integration tests should validate contract.
- Priority: Medium
- Next steps: Review controller and correlate with `TransactionService` methods.

4.1 - `UserController` TODO
- File: `src/main/java/com/orion/DigiWallet/controller/UserController.java`
- Snippet: `//TODO: 4.1` near the top
- Expectation: Ensure REST controller for `User` exists, routes for CRUD endpoints are defined, and proper response statuses and logging are present.
- Priority: Medium
- Next steps: Review `UserController` for any missing endpoints and implement them.

4.2 - `WalletController` (4.2.1..4.2.6)
- File: `src/main/java/com/orion/DigiWallet/controller/WalletController.java`
- Snippet includes:
  //TODO: 4.2.1 : Create WalletController class with necessary annotations
  //TODO: 4.2.2: Declare private final variable for WalletService
  //TODO: 4.2.3: Create a constructor that accepts WalletService as a parameter
  //TODO: 4.2.4: Implement GET WALLET BY ID API
  //TODO: 4.2.5: Implement GET WALLET BY USER ID API
  //TODO: 4.2.6: CREATE WALLET API
- Expectation / Acceptance Criteria:
  - Annotate the class with `@RestController` and `@RequestMapping("/api/wallets")`.
  - Implement endpoints:
    - `GET /api/wallets/{id}` -> returns wallet, 200 or 404
    - `GET /api/wallets/user/{userId}` -> returns wallet for user, 200 or 404
    - `POST /api/wallets` -> create wallet, return 201 CREATED
  - Log incoming requests using `logger.info("...")`.
- Dependencies: `WalletService`, `WalletRepository`, `UserRepository`.
- Priority: High (APIs used by tests and E2E flows).
- Next steps:
  1. Add `@RestController` and `@RequestMapping` annotations.
  2. Implement controller methods delegating to `WalletService`.

4.3 - `WalletControllerTest` (enable after implementing 4.2)
- File: `src/test/java/com/orion/DigiWallet/controller/WalletControllerTest.java`
- Snippet: `//TODO: 4.3: Enable tests after implementing 4.3 in WalletController`
- Expectation: Enable test class and implement test cases once controller methods exist.
- Priority: Medium
- Next steps: Implement controller, then remove `@Disabled` and run tests.

4.4 - `CardController` TODOs and response statuses (4.4.1..4.4.5)
- File: `src/main/java/com/orion/DigiWallet/controller/CardController.java`
- Snippet:
  //TODO: 4.4.1 review card controller api
  @RestController
  @RequestMapping("/api/cards")
  public class CardController {
    private final CardService cardService;
    //TODO: 4.4.2 USE REQUESTSTATUS ANNOTATION TO RETURN 201 CREATED STATUS
    @PostMapping("/create")
    public Card createCard(@RequestBody Card card) {
      return cardService.createCard(card);
    }
    //TODO: 4.4.3 USE REQUESTSTATUS ANNOTATION TO RETURN 200 OK STATUS
    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
      return cardService.getCardById(id);
    }
- Expectation / Acceptance Criteria:
  - Add `@ResponseStatus(HttpStatus.CREATED)` to create endpoint.
  - Add `@ResponseStatus(HttpStatus.OK)` (or default) to GET/PUT/DELETE endpoints as noted.
  - Ensure controller is thin — delegates to `CardService`.
- Priority: High
- Next steps:
  1. Add `@ResponseStatus` annotations as required.
  2. Ensure controller tests are implemented (see 4.5).

4.5 - `CardControllerTest` TODOs (4.5.1..4.5.6)
- File: `src/test/java/com/orion/DigiWallet/controller/CardControllerTest.java`
- Snippet shows multiple nested tests with TODO placeholders for create/get/update/delete test cases and many marked `//TODO: 4.5.x`.
- Expectation / Acceptance Criteria:
  - Implement controller tests for:
    - POST /api/cards/create -> returns created card (201)
    - GET /api/cards/{id} -> returns card (200)
    - PUT /api/cards/{id} -> returns updated card (200)
    - DELETE /api/cards/{id} -> returns success message (200 or 204)
  - Use MockMVC or `@WebMvcTest` with mocked `CardService`.
  - Remove `@Disabled` only after endpoints and service methods are implemented.
- Source marker: Tests contain TODOs and placeholders; some test methods may be currently disabled.
- Priority: High
- Next steps:
  1. Implement or mock `CardService` interactions and verify HTTP responses and JSON payloads.
  2. Remove `@Disabled` and run tests.

===============================================================================

5. Docs / Epic (5.x)

5.0 - `finalStory.md` Epic: Rewards Points Management
- File: `finalStory.md`
- Snippet at top: `//TODO: 5.0` and the Epic sections below explaining RewardsPoint feature.
- Expectation / Acceptance Criteria (summary):
  - Implement `RewardsPoint` JPA entity and repository.
  - Implement reward calculation logic in service layer, applied after DEBIT transactions (1 point per 100 units spent), idempotent per transaction and non-blocking.
  - Add `RewardsPointController` with `GET /api/rewards/wallet/{walletId}` returning 200 or 404.
  - Tests: unit service tests and E2E flow validating accumulation and DB creation.
- Dependencies: `TransactionService`, `Wallet`, DB migrations if necessary.
- Priority: High (feature-level requirement).
- Next steps:
  1. Create entity + repository (3.3 tasks).
  2. Implement service hook after transaction creation.
  3. Expose controller and write unit + integration tests.

===============================================================================

Unnumbered / Additional TODOs (no numeric prefix)
- Some TODO markers had no numeric prefix or were general comments; list them here for completeness. These appear in various files (controller reviews, tests, and repository notes). Treat them as lower priority but include as grooming items. Example:
  - `//TODO: review card controller api` in `CardController.java` (review with Swagger and tests)
  - `//TODO: REMOVE @Disabled TO ENABLE THE TESTS` in many test files — actionable once code exists.

===============================================================================

Final notes & recommendations
- Service layer is the main logical breaking point: implement all service methods before enabling controller tests. Once service methods pass unit tests, enable and implement controller tests.
- For exceptions, prefer creating a small `NotFoundException` and `DuplicateResourceException` and a `@ControllerAdvice` to map them to 404/409 HTTP responses. If you prefer minimal changes now, follow the inline TODOs and throw `RuntimeException` with the exact messages indicated (e.g., "Card not found with id: " + id).
- When enabling tests, enable them one logical group at a time (services -> repositories -> controllers) to reduce red-green cycles.
