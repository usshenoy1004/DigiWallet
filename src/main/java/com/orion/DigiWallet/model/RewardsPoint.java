package com.orion.DigiWallet.model;

//TODO: 3.3
// Define this class as a JPA entity mapped to "rewards_point" table
// ADD @table annotation to specify the table name as "rewards_point"
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
    // This value should be automatically updated whenever points are modified

    //TODO: 3.3.5
    // Generate getters and setters for all fields

    //TODO: 3.3.6
    // THIS TABLE IS NOT THERE IN MYSQL SO WHEN YOU RUN IT JPA WILL CREATE IT AUTOMATICALLY
    // GO TO MYSQL AND VERIFY IF THE TABLE IS CREATED OR NOT
    // ALSO CHECK FIELD NAMES AND TYPES
    // WITH CONSTRAINTS

}
