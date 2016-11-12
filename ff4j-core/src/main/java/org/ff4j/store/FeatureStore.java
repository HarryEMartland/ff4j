package org.ff4j.store;

import java.util.stream.Stream;

import org.ff4j.exception.GroupNotFoundException;
import org.ff4j.feature.Feature;

/**
 * Repository to persist {@link Feature}(s)
 * 
 * @author Cedrick Lunven (@clunven)
 */
public interface FeatureStore extends FF4jRepository < Feature, String > {

    /**
     * Toggle ON a feature by its identifier.
     * 
     * @param uid
     *            unique feature identifier
     */
    void enable(String uid);

    /**
     * Toggle off a feature by its identifier.
     * 
     * @param uid
     *            unique feature identifier
     */
    void disable(String uid);
   
    /**
     * Grant role on target feature.
     * 
     * @param uid
     *      feature unique identifier
     * @param roleName
     *      current role name
     */
    void grantRoleOnFeature(String uid, String roleName);

    /**
     * Remove role on target feature.
     *
       * @param uid
     *      feature unique identifier
     * @param roleName
     *      current role name
     */
    void removeRoleFromFeature(String uid, String roleName);

    /**
     * Enable all features related to the parameter group
     * 
     * @param groupName
     *            target group name
     */
    void enableGroup(String groupName);

    /**
     * Disable all features related to the parameter group
     * 
     * @param groupName
     *            target group name
     */
    void disableGroup(String groupName);

    /**
     * Check if current group exist or not.
     * 
     * @param groupName
     *            target group name
     */
    boolean existGroup(String groupName);

    /**
     * Read all features within target group.
     * 
     * @param groupName
     *            target group name
     * @return return all feature from group or groupnotfoundException if does not exist
     * 
     * @throws GroupNotFoundException
     *              if group does not exist
     */
    Stream < Feature> readGroup(String groupName);
    
    /**
     * Add target {@link Feature} to target group.
     * 
     * @param uid
     *            target feature identifier
     * @param groupName
     *            target groupName
     */
    void addToGroup(String uid, String groupName);
    
    /**
     * Remove target {@link Feature} from group.
     * 
     * @param uid
     *            target feature identifier
     * @param groupName
     *            target groupName
     */
    void removeFromGroup(String uid, String groupName);
    
    /**
     * Return a set of existing groups.
     * 
     * @return set of group in the store
     */
    Stream < String > readAllGroups();
    
    /**
     * Initialize the target database schema by creating expected structures.
     * 
     * <li> TABLE, INDEX will be created for JDBC, but also COLLECTION and INDEXS for MongoDb, or COLUMN FAMILY for Cassandra.
     * <li> The structures will be created only if they don't exist.
     * <li> In some cases, there is nothing todo (Ehcache, Redis, InMemory), the method won't failed but do nothing (it does not clear the DB) 
     * 
     * @since 1.6
     */
    void createSchema();
    
}
