package com.intranet.onlineshop.unit.service;

import com.intranet.onlineshop.domain.entities.PostOffice;
import com.intranet.onlineshop.domain.entities.PostOfficeConnection;
import com.intranet.onlineshop.domain.models.service.NodeStateModel;
import com.intranet.onlineshop.domain.models.service.PostOfficeServiceModel;
import com.intranet.onlineshop.repository.PostOfficeConnectionRepository;
import com.intranet.onlineshop.repository.PostOfficeRepository;
import com.intranet.onlineshop.service.PostOfficesMapServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.when;
public class PostOfficeMapServiceTest {

    private PostOfficeConnectionRepository postOfficeConnectionRepository;
    private PostOfficeRepository postOfficeRepository;

    @BeforeEach
    public void initializeRepositories() {
         postOfficeConnectionRepository = Mockito.mock(PostOfficeConnectionRepository.class);
         postOfficeRepository = Mockito.mock(PostOfficeRepository.class);
    }
    @Test
    public void testFindShortestPaths() {
        when(postOfficeConnectionRepository.findAll()).thenReturn(getMockedPostOfficeConnections());
        PostOfficesMapServiceImpl service =
                new PostOfficesMapServiceImpl(postOfficeConnectionRepository, postOfficeRepository, new ModelMapper());
        Map<String, NodeStateModel> result = service.findShortestPathsFrom("A");

        int[] actualDistancePathsFromA = {
                result.get("C").getPathDistance(),
                result.get("B").getPathDistance(),
                result.get("E").getPathDistance(),
                result.get("D").getPathDistance(),
                result.get("G").getPathDistance(),
                result.get("F").getPathDistance()
        };
        int[] expectedDistancePathsFromA = {3, 4, 7, 9, 12, 11};
        Assert.assertArrayEquals(expectedDistancePathsFromA, actualDistancePathsFromA);
    }
    @Test
    public void testFindFirstCommonPostOfficeMethod() {
        String startNodeName = "A";
        List<String> targetNodeNames = List.of("R", "Q", "P", "V", "K");
        when(postOfficeRepository.findByOfficeName("B")).thenReturn(new PostOffice() {{
            setOfficeName("B");
            setAddress(null);
        }});
        PostOfficesMapServiceImpl service =
                new PostOfficesMapServiceImpl(postOfficeConnectionRepository, postOfficeRepository, new ModelMapper());
        PostOfficeServiceModel postOffice =
                service.findFirstCommonPostOffice(startNodeName, TreeModelTest.getMockNodesInfo(), targetNodeNames);
        Assert.assertNotEquals("B", postOffice.getOfficeName());
    }
    private List<PostOfficeConnection> getMockedPostOfficeConnections() {
        return List.of(createConnection("A", "B", 4),
                createConnection("A", "E", 7),
                createConnection("E", "D", 2),
                createConnection("B", "D", 5),
                createConnection("E", "G", 5),
                createConnection("D", "G", 10),
                createConnection("D", "F", 2),
                createConnection("F", "G", 3),
                createConnection("B", "C", 6),
                createConnection("A", "C", 3),
                createConnection("E", "C", 8),
                createConnection("D", "C", 11));
    }

    private PostOfficeConnection createConnection(String from, String to, int distance) {
        return new PostOfficeConnection() {{
           setFirstPostOfficeName(from);
           setSecondPostOfficeName(to);
           setDistance(distance);
        }};
    }
}
