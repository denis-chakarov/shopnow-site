package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.NodeStateModel;
import com.intranet.onlineshop.domain.models.service.PostOfficeServiceModel;

import java.util.List;
import java.util.Map;

/**
 * Class used for declaring the business logic methods regarding the
 *  graph structure of the post offices
 */
public interface PostOfficesMapService {

    /**
     * find the shortest paths information in the graph structure
     * from a starting post office to all others
     * @param postOfficeName the starting post office name
     * @return return  a map of all post office names and information
     * calculated from the algorithm for every post office
     */
    Map<String, NodeStateModel> findShortestPathsFrom(String postOfficeName);

    /**
     * find the first common post office in a tree structure of post offices
     * @param startNode the starting node name(the root of the tree structure)
     * @param nodes the post offices info needed to build the tree structure
     * @param targetNodes the target post office names where the order's products are at.
     * @return returns a service model of the wanted common post office
     */
    PostOfficeServiceModel findFirstCommonPostOffice(String startNode, Map<String, NodeStateModel> nodes, List<String> targetNodes);
}
