package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.PostOffice;
import com.intranet.onlineshop.domain.models.service.NodeStateModel;
import com.intranet.onlineshop.domain.models.service.PostOfficeServiceModel;

import java.util.List;
import java.util.Map;

public interface PostOfficesMapService {

    Map<String, NodeStateModel> findShortestPathsFrom(String postOfficeName);

    PostOfficeServiceModel findFirstCommonPostOffice(String startNode, Map<String, NodeStateModel> nodes, List<String> targetNodes);
}
