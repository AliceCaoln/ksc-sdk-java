package com.ksc.network.slb;

import com.ksc.model.Filter;
import com.ksc.network.slb.model.*;
import com.ksc.regions.InMemoryRegionImpl;
import com.ksc.regions.Region;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KSCSLBClientTest {
	private static final Logger log = Logger.getLogger(KSCSLBClientTest.class);

	@Test
	public void describeLoadBalancers(){
		KSCSLBClient client=new KSCSLBClient();
		client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
		client.setServiceNameIntern("slb");
		DescribeLoadBalancersRequest request=new DescribeLoadBalancersRequest();
//		request.addLoadBalancerIds("521a2e79-89f8-4a26-9e86-6ea9878d00eb");
//		request.setState("associate");
		//request.withVpcIds("147d81eb-f780-434d-8355-dc125013520e");
        request.addProjectIds("472");
		DescribeLoadBalancersResult result=client.describeLoadBalancers(request);
		log.info(result);
	}

	@Test
    public void createLoadBalancer(){
	    KSCSLBClient client = new KSCSLBClient();
	    client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
	    client.setServiceNameIntern("slb");
        CreateLoadBalancerRequest request = new CreateLoadBalancerRequest();
        request.setVpcId("9f0cd9aa-6a14-4a0d-88c4-530f559b13b2");
        request.setLoadBalancerName("sdk-test");
        //request.setSubnetId("73447731-8071-4a2c-b405-38dcb3b88544");
        //request.setType("internal");
        request.setProjectId("472");
        CreateLoadBalancerResult result = client.createLoadBalancer(request);
        log.info(result);
    }

    @Test
    public void deleteLoadBalancer() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        DeleteLoadBalancerRequest request = new DeleteLoadBalancerRequest();
        request.setLoadBalancerId("60c3df39-bfd2-473b-a1cd-e0a11ecace5e");
        DeleteLoadBalancerResult result = client.deleteLoadBalancer(request);
        log.info(result);
    }

    @Test
    public void modifyLoadBalancer() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        ModifyLoadBalancerRequest request = new ModifyLoadBalancerRequest();
        request.setLoadBalancerId("10d7f195-e905-4ca1-973b-1a31d37cdd1a");
//        request.setLoadBalancerName("mmd");
        request.setLoadBalancerState("stop");
        ModifyLoadBalancerResult result = client.modifyLoadBalancer(request);
        log.info(result);
    }

    @Test
    public void createListeners(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        CreateListenersRequest request = new CreateListenersRequest();
        request.setLoadBalancerId("10d7f195-e905-4ca1-973b-1a31d37cdd1a");
        request.setListenerState("start");
        request.setListenerName("qnmd");
        request.setListenerProtocol("TCP");
//        request.setCertificateId("TCP ");
        request.setListenerPort("500");
        request.setMethod("RoundRobin");
        request.setSessionState("start");
//        request.setSessionPersistencePeriod((long) 3600);
//        request.setCookieType("lll");
//        request.setCookieName("lll");
        CreateListenersResult result = client.createListeners(request);
        log.info(result);
    }

    @Test
    public void modifyListeners() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        ModifyListenersRequest request = new ModifyListenersRequest();
        request.setListenerId("a413d3d4-29f5-4cb3-9c26-136be2681255");
        request.setListenerName("ahf");
        request.setListenerState("start");
        request.setSessionState("start");
        request.setMethod("RoundRobin");
        request.setSessionPersistencePeriod(Long.valueOf("3600"));
//        request.setCookieType("start");
//        request.setCookieName("start");
        ModifyListenersResult result = client.modifyListeners(request);
        log.info(result);
    }

    @Test
    public void deleteListeners() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        DeleteListenersRequest request = new DeleteListenersRequest();
        request.setListenerId("cfba25d1-71e4-4d52-8f79-70d115367abd");
        DeleteListenersResult result = client.deleteListeners(request);
        log.info(result);
    }

    @Test
    public void describeListeners(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        DescribeListenersRequest request=new DescribeListenersRequest();
//		request.addListenerIds("521a2e79-89f8-4a26-9e86-6ea9878d00eb");
        //request.withLoadBalancerIds("ec605c5c-ac74-419b-9138-2c3bf06af47e");
        DescribeListenersResult result=client.describeListeners(request);
        log.info(result);
    }

    @Test
    public void configureHealthCheck(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        ConfigureHealthCheckRequest request = new ConfigureHealthCheckRequest();
        request.setListenerId("a413d3d4-29f5-4cb3-9c26-136be2681255");
        request.setHealthCheckState("start");
        request.setHealthyThreshold(10);
        request.setInterval(20);
        request.setTimeout(9);
        request.setUnhealthyThreshold(8);
//        request.setUrlPath("\");
//        request.setIsDefaultHostName("lll");
//        request.setHostName("lll");
        ConfigureHealthCheckResult result = client.configureHealthCheck(request);
        log.info(result);
    }

    @Test
    public void modifyHealthCheck(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        ModifyHealthCheckRequest request = new ModifyHealthCheckRequest();
        request.setHealthCheckId("533ace44-6082-4f05-b1a3-bd640f8989f8");
        request.setHealthCheckState("start");
        request.setHealthyThreshold(1);
        request.setInterval(1);
        request.setTimeout(1);
        request.setUnhealthyThreshold(1);
//        request.setUrlPath("\");
//        request.setIsDefaultHostName("lll");
//        request.setHostName("lll");
        ModifyHealthCheckResult result = client.modifyHealthCheck(request);
        log.info(result);
    }

    @Test
    public void deleteHealthCheck() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        DeleteHealthCheckRequest request = new DeleteHealthCheckRequest();
        request.setHealthCheckId("f0d12cc3-dd77-4e21-be91-53c8d39ef17e");
        DeleteHealthCheckResult result = client.deleteHealthCheck(request);
        log.info(result);
    }

    @Test
    public void describeHealthChecks(){
        KSCSLBClient client=new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        DescribeHealthChecksRequest request=new DescribeHealthChecksRequest();
//		request.addLoadBalancerIds("521a2e79-89f8-4a26-9e86-6ea9878d00eb");
//		request.setState("associate");
        //request.withVpcIds("147d81eb-f780-434d-8355-dc125013520e");
        DescribeHealthChecksResult result=client.describeHealthChecks(request);
        log.info(result);
    }

    @Test
    public void registerInstancesWithListener(){
        KSCSLBClient client=new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        RegisterInstancesWithListenerRequest request=new RegisterInstancesWithListenerRequest();
		request.setListenerId("a413d3d4-29f5-4cb3-9c26-136be2681255");
        request.setRealServerType("Ipfwd");
        request.setRealServerIp("120.92.128.161");
        request.setRealServerPort(22);
        request.setWeight(10);
        RegisterInstancesWithListenerResult result=client.registerInstancesWithListener(request);
        log.info(result);
    }

    @Test
    public void modifyInstancesWithListener(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        ModifyInstancesWithListenerRequest request = new ModifyInstancesWithListenerRequest();
        request.setRegisterId("a8e462c3-c9da-4a85-8157-d7eb523ee72f");
        request.setRealServerPort(10);
        request.setWeight(12);
        ModifyInstancesWithListenerResult result = client.modifyInstancesWithListener(request);
        log.info(result);
    }

    @Test
    public void deregisterInstancesFromListener() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        DeregisterInstancesFromListenerRequest request = new DeregisterInstancesFromListenerRequest();
        request.setRegisterId("a8e462c3-c9da-4a85-8157-d7eb523ee72f");
        DeregisterInstancesFromListenerResult result = client.deregisterInstancesFromListener(request);
        log.info(result);
    }

    @Test
    public void describeInstancesWithListener(){
        KSCSLBClient client=new KSCSLBClient();
        client.setEndpoint("http://slb.cn-shanghai-3.api.ksyun.com");
        client.setServiceNameIntern("slb");
        DescribeInstancesWithListenerRequest request=new DescribeInstancesWithListenerRequest();
//		request.addLoadBalancerIds("521a2e79-89f8-4a26-9e86-6ea9878d00eb");
//		request.setState("associate");
        //request.withVpcIds("147d81eb-f780-434d-8355-dc125013520e");
        DescribeInstancesWithListenerResult result=client.describeInstancesWithListener(request);
        log.info(result);
    }

    @Test
    public void createHostHeader(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        CreateHostHeaderRequest request = new CreateHostHeaderRequest();
        request.setHostHeader("www.baidu.stop1");
        request.setListenerId("eb9729de-93e6-48c6-946e-f75cb92ccb26");

        CreateHostHeaderResult result = client.createHostHeader(request);
        log.info(result);
    }

    @Test
    public void modifyHostHeader(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        ModifyHostHeaderRequest request = new ModifyHostHeaderRequest();
        request.setHostHeaderId("04f81a2e-bb2a-46e4-9dff-ec4510cc7636");
        request.setCertificateId("8998c583-d2b2-4141-ae40-d62286d58051");

        ModifyHostHeaderResult result = client.modifyHostHeader(request);
        log.info(result);
    }

    @Test
    public void deleteHostHeader(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DeleteHostHeaderRequest request = new DeleteHostHeaderRequest();
        request.setHostHeaderId("b2ed60ce-8377-48ac-bbe4-d43215275629");

        DeleteHostHeaderResult result = client.deleteHostHeader(request);
        log.info(result);
    }

    @Test
    public void describeHostHeader(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DescribeHostHeadersRequest request = new DescribeHostHeadersRequest();
        /*List<String> values = new ArrayList<String>();
        values.add("eb9729de-93e6-48c6-946e-f75cb92ccb26");
        Filter filter = new Filter("listener-id", values);
        request.addFilters(filter);*/
        DescribeHostHeadersResult result = client.describeHostHeader(request);
        log.info(result);
    }

    @Test
    public void createLoadBalancerAcl(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        CreateLoadBalancerAclRequest request = new CreateLoadBalancerAclRequest();
        request.setLoadBalancerAclName("test");
        request.setIpVersion("ipv4");
        CreateLoadBalancerAclResult result = client.createLoadBalancerAcl(request);
        log.info(result);
    }

    @Test
    public void deleteLoadBalancerAcl(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DeleteLoadBalancerAclRequest request = new DeleteLoadBalancerAclRequest();
        request.setLoadBalancerAclId("1b69fc05-c080-400a-8f8d-21b5cb58a7ff");
        DeleteLoadBalancerAclResult result = client.deleteLoadBalancerAcl(request);
        log.info(result);
    }

    @Test
    public void modifyLoadBalancerAcl(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        ModifyLoadBalancerAclRequest request = new ModifyLoadBalancerAclRequest();
        request.setLoadBalancerAclId("1b69fc05-c080-400a-8f8d-21b5cb58a7ff");
        request.setLoadBalancerAclName("test1");
        ModifyLoadBalancerAclResult result = client.modifyLoadBalancerAcl(request);
        log.info(result);
    }

    @Test
    public void describeLoadBalancerAcls(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DescribeLoadBalancerAclsRequest request = new DescribeLoadBalancerAclsRequest();
        com.ksc.internal.SdkInternalList<String> ids = new com.ksc.internal.SdkInternalList<String>();
        ids.add("e3ff23b1-2f14-41bc-aada-fd21df585f47");
        request.setLoadBalancerAclIds(ids);
        DescribeLoadBalancerAclsResult result = client.describeLoadBalancerAcls(request);
        log.info(result);
    }

    @Test
    public void deleteLoadBalancerAclEntry(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DeleteLoadBalancerAclEntryRequest request = new DeleteLoadBalancerAclEntryRequest();
        request.setLoadBalancerAclEntryId("e357ee65-a123-47b4-ab55-c07e2dd2c63b");
        request.setLoadBalancerAclId("1b69fc05-c080-400a-8f8d-21b5cb58a7ff");
        DeleteLoadBalancerAclEntryResult result = client.deleteLoadBalancerAclEntry(request);
        log.info(result);
    }

    @Test
    public void createLoadBalancerAclEntry(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        CreateLoadBalancerAclEntryRequest request = new CreateLoadBalancerAclEntryRequest();
        request.setCidrBlock("6.0.0.0/8");
        request.setLoadBalancerAclId("1b69fc05-c080-400a-8f8d-21b5cb58a7ff");
        request.setProtocol("ip");
        request.setRuleAction("allow");
        request.setRuleNumber(170);
        CreateLoadBalancerAclEntryResult result = client.createLoadBalancerAclEntry(request);
        log.info(result);
    }

    @Test
    public void associateLoadBalancerAcl(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        AssociateLoadBalancerAclRequest request = new AssociateLoadBalancerAclRequest();
        request.setLoadBalancerAclId("1b69fc05-c080-400a-8f8d-21b5cb58a7ff");
        request.setListenerId("2d8c3d4f-365c-4dd8-ac3d-68038ccbedff");
        AssociateLoadBalancerAclResult result = client.associateLoadBalancerAcl(request);
        log.info(result);
    }

    @Test
    public void disassociateLoadBalancerAcl(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DisassociateLoadBalancerAclRequest request = new DisassociateLoadBalancerAclRequest();
        request.setListenerId("2d8c3d4f-365c-4dd8-ac3d-68038ccbedff");
        DisassociateLoadBalancerAclResult result = client.disassociateLoadBalancerAcl(request);
        log.info(result);
    }

    @Test
    public void createSlbRule(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        CreateSlbRuleRequest request = new CreateSlbRuleRequest();
        request.setBackendServerGroupId("ceb181ff-67c6-4b3d-b74a-3929583aac7b");
        request.setSessionState("stop");
        request.setHealthCheckState("start");
        request.setHostHeaderId("92391365-d37f-4f35-b3b9-7db72dca6b00");
        request.setUrlPath("/1d");
        request.setHealthyThreshold(5);
        request.setInterval(3);
        request.setTimeout(3);
        request.setUnhealthyThreshold(5);
        request.setCookieName("saa");
        request.setCookieType("ImplantCookie");
        request.setPath("/sdsf1sf");
        request.setListenerSync("off");
        request.setMethod("LeastConnections");
        CreateSlbRuleResult result = client.createSlbRule(request);
        log.info(result);
    }

    @Test
    public void modifySlbRule(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        ModifySlbRuleRequest request = new ModifySlbRuleRequest();
        request.setRuleId("1126f5b3-93e8-4c86-9b1a-662e07c6fbd9");
        request.setBackendServerGroupId("ceb181ff-67c6-4b3d-b74a-3929583aac7b");
        ModifySlbRuleResult result = client.modifySlbRule(request);
        log.info(result);
    }

    @Test
    public void deleteRule(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DeleteRuleRequest request = new DeleteRuleRequest();
        request.setRuleId("03c405df-498c-40e0-9e1a-bc5dfd61d012");
        DeleteRuleResult result = client.deleteRule(request);
        log.info(result);
    }

    @Test
    public void describeRules(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DescribeRulesRequest request = new DescribeRulesRequest();
        request.setMaxResults("10");
        //request.addRuleIds("03c405df-498c-40e0-9e1a-bc5dfd61d012");
        /*List<String> values = new ArrayList<String>();
        values.add("772b5013-a058-465f-90b5-df2eba3bf907");
        Filter filter = new Filter("host-header-id", values);
        request.addFilters(filter);*/
        DescribeRulesResult result = client.describeRules(request);
        log.info(result);
    }

    @Test
    public void createBackendServerGroup(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        CreateBackendServerGroupRequest request = new CreateBackendServerGroupRequest();
        request.setBackendServerGroupName("test-sdk");
        request.setBackendServerGroupType("Mirror");
        request.setVpcId("f908be08-58f0-48e2-b7fa-e438d057a94d");
        request.setHealthCheckState("start");
        request.setHealthyThreshold(5);
        request.setUnhealthyThreshold(5);
        request.setInterval(3);
        request.setTimeout(5);
        request.setUrlPath("/");
        CreateBackendServerGroupResult result = client.createBackendServerGroup(request);
        log.info(result);
    }

    @Test
    public void modifyBackendServerGroup(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        ModifyBackendServerGroupRequest request = new ModifyBackendServerGroupRequest();
        request.setBackendServerGroupId("d62e8319-5342-4651-a296-843fea9ae62b");
        request.setBackendServerGroupName("abc-mirror-2");
        ModifyBackendServerGroupResult result = client.modifyBackendServerGroup(request);
        log.info(result);
    }

    @Test
    public void deleteBackendServerGroup(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DeleteBackendServerGroupRequest request = new DeleteBackendServerGroupRequest();
        request.setBackendServerGroupId("d2fb12c0-52fa-4b4f-8a10-e90165b1217a");
        DeleteBackendServerGroupResult result = client.deleteBackendServerGroup(request);
        log.info(result);
    }

    @Test
    public void describeBackendServerGroups(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DescribeBackendServerGroupsRequest request = new DescribeBackendServerGroupsRequest();
        /*request.addBackendServerGroupIds("d2fb12c0-52fa-4b4f-8a10-e90165b1217a");
        List<String> values = new ArrayList<String>();
        values.add("eb9729de-93e6-48c6-946e-f75cb92ccb26");
        Filter filter = new Filter("vpc-id", values);
        request.addFilters(filter);*/
        DescribeBackendServerGroupsResult result = client.describeBackendServerGroups(request);
        log.info(result);
    }

    @Test
    public void registerBackendServer(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        RegisterBackendServerRequest request = new RegisterBackendServerRequest();
        request.setBackendServerGroupId("aabec64a-0e6e-44c3-a915-7367b3d087de");
        request.setBackendServerIp("155.0.30.36");
        request.setBackendServerPort("9090");
        request.setWeight(100);
        RegisterBackendServerResult result = client.registerBackendServer(request);
        log.info(result);
    }

    @Test
    public void modifyBackendServer(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        ModifyBackendServerRequest request = new ModifyBackendServerRequest();
        request.setRegisterId("3c5410a2-4c88-4a3b-ab6e-d8a126f6a9af");
        request.setWeight(30);
        ModifyBackendServerResult result = client.modifyBackendServer(request);
        log.info(result);
    }

    @Test
    public void deregisterBackendServer(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DeregisterBackendServerRequest request = new DeregisterBackendServerRequest();
        request.setRegisterId("e844ef08-5e89-4589-ab52-4eed1852e96e");
        DeregisterBackendServerResult result = client.deregisterBackendServer(request);
        log.info(result);
    }

    @Test
    public void describeBackendServers(){
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DescribeBackendServersRequest request = new DescribeBackendServersRequest();
        request.addRegisterIds("3c5410a2-4c88-4a3b-ab6e-d8a126f6a9af");
        List<String> values = new ArrayList<String>();
        values.add("aabec64a-0e6e-44c3-a915-7367b3d087de");
        Filter filter = new Filter("backend-server-group-id", values);
        request.addFilters(filter);
        DescribeBackendServersResult result = client.describeBackendServers(request);
        log.info(result);
    }

    @Test
    public void createPrivateLinkServer() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        CreatePrivateLinkServerRequest request = new CreatePrivateLinkServerRequest();
        request.setPrivateLinkServerName("hss-test-001");
        request.setListenerId("b5230cfe-362a-49e0-b63b-8fe23e758609");
        request.setDescription("123123");
        request.setProjectId("0");
        CreatePrivateLinkServerResult result = client.createPrivateLinkServer(request);
        log.info(result);
    }

    @Test
    public void describePrivateLinkServer() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DescribePrivateLinkServerRequest request = new DescribePrivateLinkServerRequest();
        request.addPrivateLinkServerIds("140819b8-bbfb-4710-b6c9-08151bf802cf");

        DescribePrivateLinkServerResult result = client.describePrivateLinkServer(request);
        log.info(result);
    }

    @Test
    public void modifyPrivateLinkServer() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        ModifyPrivateLinkServerRequest request = new ModifyPrivateLinkServerRequest();
        request.setPrivateLinkServerName("hss-test-123123");
        request.setPrivateLinkServerId("140819b8-bbfb-4710-b6c9-08151bf802cf");
        request.setDescription("321312");

        ModifyPrivateLinkServerResult result = client.modifyPrivateLinkServer(request);
        log.info(result);
    }

    @Test
    public void deletePrivateLinkServer() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DeletePrivateLinkServerRequest request = new DeletePrivateLinkServerRequest();
        request.setPrivateLinkServerId("140819b8-bbfb-4710-b6c9-08151bf802cf");

        DeletePrivateLinkServerResult result = client.deletePrivateLinkServer(request);
        log.info(result);
    }

    @Test
    public void associatePrivateLinkServer() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        AssociatePrivateLinkServerRequest request = new AssociatePrivateLinkServerRequest();
        request.setPrivateLinkServerId("8721d4a3-08b2-4c88-bfd4-21ee4aa6c5d1");
        request.setLoadBalancerId("ec85068f-e441-4066-a48f-8ac6b2f4e044");
        request.setListenerPort("8090");
        request.setProjectId("0");

        AssociatePrivateLinkServerResult result = client.associatePrivateLinkServer(request);
        log.info(result);
    }

    @Test
    public void describePrivateLink() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DescribePrivateLinkRequest request = new DescribePrivateLinkRequest();
        request.addPrivateLinkIds("61b0ffcf-32bf-400b-8604-c25f2b58b063");

        DescribePrivateLinkResult result = client.describePrivateLink(request);
        log.info(result);
    }

    @Test
    public void deletePrivateLink() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        DeletePrivateLinkRequest request = new DeletePrivateLinkRequest();
        request.setPrivateLinkId("61b0ffcf-32bf-400b-8604-c25f2b58b063");

        DeletePrivateLinkResult result = client.deletePrivateLink(request);
        log.info(result);
    }


    @Test
    public void listPrivateLinkServer() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        ListPrivateLinkServerRequest request = new ListPrivateLinkServerRequest();
        request.setPrivateLinkServerId("140819b8-bbfb-4710-b6c9-08151bf802cf");

        ListPrivateLinkServerResult result = client.listPrivateLinkServer(request);
        log.info(result);
    }

    @Test
    public void removePrivateLink() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        RemovePrivateLinkRequest request = new RemovePrivateLinkRequest();
        request.setPrivateLinkId("ea9521a6-4b51-4912-b0ed-ac2a5c1dd0b0");

        RemovePrivateLinkResult result = client.removePrivateLink(request);
        log.info(result);
    }


    @Test
    public void acceptPrivateLink() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        AcceptPrivateLinkRequest request = new AcceptPrivateLinkRequest();
        request.setPrivateLinkId("9f69e757-d33d-45db-a1af-8f7953d74d64");

        AcceptPrivateLinkResult result = client.acceptPrivateLink(request);
        log.info(result);
    }


    @Test
    public void rejectPrivateLink() {
        KSCSLBClient client = new KSCSLBClient();
        client.setEndpoint("http://eip.inner.api.ksyun.com");
        client.setServiceNameIntern("slb");
        Region region = new Region(new InMemoryRegionImpl("cn-shanghai-3", null));
        client.setRegion(region);
        RejectPrivateLinkRequest request = new RejectPrivateLinkRequest();
        request.setPrivateLinkId("ea9521a6-4b51-4912-b0ed-ac2a5c1dd0b0");

        RejectPrivateLinkResult result = client.rejectPrivateLink(request);
        log.info(result);
    }

}
