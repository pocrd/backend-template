package net.pocrd.demo.autotest.test;

import net.pocrd.m.app.client.ApiAccessor;
import net.pocrd.m.app.client.ApiContext;
import net.pocrd.m.app.client.BaseRequest;
import net.pocrd.m.app.client.api.request.*;
import net.pocrd.m.app.client.api.resp.Api_DEMO_DemoEntity;
import net.pocrd.m.app.client.util.Base64Util;
import net.pocrd.m.app.client.util.RsaHelper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by guankaiqiang521 on 2014/9/29.
 */
public class DemoTest {
    private static final String url          = "http://localhost:8080/m.api";
    private static final String deviceId     = "1414807058834";
    private static final String deviceSecret = "581bb3c7f2d09e4d2f07f69706fff13f261f4cfa2038cd2ab7bb46040ca2d568";
    private static final String deviceToken  = "jxpvuVNWcYb75UlLHC3QyptGUwn0V+LDzdi/GMTLcmGN1rmpX80ze7hRE8peb0dbjfUWi52dEoaZy6YCJZcF9L4f+2gJXMjncRCFhGY3AHo=";
    private static final long   userId       = 22L;
    private static final String userToken    = "A/vUrHrdp/9Qs1SejYBFY/q/e6XGIWTAJzH0uWXNvrMsLPIMOkjxAVyODDeu+JLA3pm/ASMcOcnvlRTk8APu1xAWxMc019l2ijGJ+CLyaFs=";

    private ApiContext  context  = ApiContext.getDefaultContext("1", 123, "1.2.3");
    private ApiAccessor accessor = new ApiAccessor(context, 3000, 30000, "test-agent", url);

    private void initWithDeviceInfo(ApiContext context) {
        context.setDeviceInfo(deviceId, deviceSecret, deviceToken);
    }

    private void initWithUserInfo(ApiContext context) {
        initWithDeviceInfo(context);
        context.setUserInfo(userId, userToken, Long.MAX_VALUE);
    }

    @BeforeClass
    public static void init() {
        System.setProperty("debug.dubbo.url", "dubbo://10.32.184.32:20880/");
        System.setProperty("debug.dubbo.version", "LATEST");
    }

    @Test
    public void sayHelloTest() {
        final Demo_SayHello sayHello = new Demo_SayHello("abc");

        accessor.fillApiResponse(sayHello);
        Api_DEMO_DemoEntity resp = sayHello.getResponse();
        Assert.assertEquals(ApiCode.SUCCESS, sayHello.getReturnCode());
        Assert.assertEquals(123, resp.id);
        Assert.assertEquals("abc", resp.name);
    }

    @Test
    public void tryErrorTest() {
        final Demo_TryError tryError = new Demo_TryError("abc");

        accessor.fillApiResponse(tryError);
        Assert.assertEquals(-220, tryError.getReturnCode());
    }

    @Test
    public void testRegistedDevice() {
        initWithDeviceInfo(context);
        final Demo_TestRegistedDevice regiestedDevice = new Demo_TestRegistedDevice();
        accessor.fillApiResponse(regiestedDevice);
        Assert.assertEquals(ApiCode.SUCCESS, regiestedDevice.getReturnCode());
    }

    @Test
    public void testUserLogin() {
        initWithUserInfo(context);
        Demo_TestUserLogin userLogin = new Demo_TestUserLogin();
        accessor.fillApiResponse(userLogin);
        Assert.assertEquals(ApiCode.SUCCESS, userLogin.getReturnCode());
    }

    @Test
    public void testReirectUrl() {
        final Demo_TestRedirect req = new Demo_TestRedirect("A", "456");
        accessor.fillApiResponse(req);
        String msg = req.getResponse();
        Assert.assertEquals("PROD", msg);
    }

    public static final Comparator<String> StringComparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            int n1 = s1 == null ? 0 : s1.length();
            int n2 = s2 == null ? 0 : s2.length();
            int mn = n1 < n2 ? n1 : n2;
            for (int i = 0; i < mn; i++) {
                int k = s1.charAt(i) - s2.charAt(i);
                if (k != 0) {
                    return k;
                }
            }
            return n1 - n2;
        }
    };

    @Test
    public void testIntegrated() throws UnsupportedEncodingException {
        Map<String, String> mapping = new HashMap<String, String>();
        //1.设置请求参数
        mapping.put("_mt", "demo.getResByThirdPartyId");//方法名
        mapping.put("_tpid", "1");//tpid即集成第三方的编号（由网关统一分配），爬虫暂且用1
        mapping.put("in", "abcde xxxx");//其他方法入参
        //2.进行签名
        StringBuilder sb = new StringBuilder(128);
        String[] array = mapping.keySet().toArray(new String[mapping.size()]);
        if (array.length > 0) {
            Arrays.sort(array, StringComparator);
            for (String key : array) {
                sb.append(key);
                sb.append("=");
                sb.append(mapping.get(key));
            }
        }
        System.out.println("before sig:" + sb.toString());
        //分配给爬虫的对应公私钥对
        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyYhw3zrUeFCmvuu82VAkFIX6NKtQPGdKAWVFYhXR9BwFeELmehdEUwcwoHECkzDN4DArsHegWx1nkv4S1+Yz3YIWc0eO2TQgQISw0moj7seqFiAwxzYko5BApabaXQJfR/veGWakEvJCk+jTrH/R6nv1V+8g71HWqnPKBbEdsyQIDAQAB";
        String pri = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALJiHDfOtR4UKa+67zZUCQUhfo0q1A8Z0oBZUViFdH0HAV4QuZ6F0RTBzCgcQKTMM3gMCuwd6BbHWeS/hLX5jPdghZzR47ZNCBAhLDSaiPux6oWIDDHNiSjkEClptpdAl9H+94ZZqQS8kKT6NOsf9Hqe/VX7yDvUdaqc8oFsR2zJAgMBAAECgYBgjYw6hM8x/bXmoWczX98WAOAv5turZM20nSPTp0C7H9yUnrbp4AKgmpk3qLswuDqvos0Sqslh8vtsPmHF4dJzdfXHBHDec93O/b4QTlKr6tTEPdjwkF/JU3mgMQZsNEUdmVHfNG2owsI+0VEfHMfn09VIgs4SQjSbijIQ7Td6VQJBAPbE5m3Q1dUfuDCHuxQrRCIcH8UWTgDLwqvFtfRiD+/C6jpsrarXHUIuxgiJ1jVq1TiE0X/pNc6oUBWJZNXJow8CQQC5DlYH/R573/r2al1y6sYmgGmneHeEbOffzngzxqU+8GNAIhWN1yC2DOdiMUCmgVP34WG4WcIpWHzAkfUnSRKnAkAzizM6cumHR8XYVTGNZ/AmU8uLBjqqzeTOrlBwSF9dzE/SfkrUKXSSE2UH+YqFw9ffo1aDKjoz/VIk/XrTcPefAkEAmN/a+maEVFlH/WEJKfIBF7Vlks/WDDPbqevrKPqlcEUt+MEvhSl/AGXQkDGX8vVL5K7wB1c/KuDKzlrFZ1raaQJBAOrZPzsHcsOS91fwRyVF37vdtRUS0YTMKnFAKI0254UXKbmzbqOSwKC3hkYcu9jIzWkMk8kB2SMFqh9+xPsTNTc=";
        RsaHelper rsaHelper = new RsaHelper(pub, pri);
        String sig = Base64Util.encodeToString(rsaHelper.sign(sb.toString().getBytes("utf-8")));
        mapping.put("_sig", sig);
        System.out.println("sig:" + sig);
        //3.构造请求
        StringBuilder req = new StringBuilder();
        for (Entry<String, String> entry : mapping.entrySet()) {
            req.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "utf-8")).append("&");//进行url encoding
        }
        System.out.println("http://127.0.0.1:8080/m.api?" + req.toString());
    }

    @Test
    public void testMockObject() {
        final Demo_TestMock testMock = new Demo_TestMock("NAME");
        accessor.fillApiResponse(testMock);
        Api_DEMO_DemoEntity resp = testMock.getResponse();
        Assert.assertEquals(ApiCode.SUCCESS, testMock.getReturnCode());
        Assert.assertEquals(resp.id, 1234567);
        Assert.assertEquals(resp.name, "mock test");
    }

    @Test
    public void testShortCircuit() {
        final Demo_TestShortCircuit testMock = new Demo_TestShortCircuit("NAME");
        accessor.fillApiResponse(testMock);
        Api_DEMO_DemoEntity resp = testMock.getResponse();
        Assert.assertEquals(ApiCode.SUCCESS, testMock.getReturnCode());
        Assert.assertEquals(resp.id, 1234567);
        Assert.assertEquals(resp.name, "mock test");
    }

    @Test
    public void testMockService() {
        final Demo_TestMockService testMock = new Demo_TestMockService("NAME");
        accessor.fillApiResponse(testMock);
        Api_DEMO_DemoEntity resp = testMock.getResponse();
        Assert.assertEquals(ApiCode.SUCCESS, testMock.getReturnCode());
        Assert.assertEquals(resp.id, 7654321);
        Assert.assertEquals(resp.name, "mock service test NAME");
    }

    @Test
    public void testIgnoreParameterForSecurity() {
        final Demo_TestMockService testMock = new Demo_TestMockService("NAME");
        final Demo_TestIgnoreParameterForSecurity testIgnoreParameterForSecurity = new Demo_TestIgnoreParameterForSecurity("hahaha");
        final BaseRequest[] reqs = new BaseRequest[] { testMock, testIgnoreParameterForSecurity };
        accessor.fillApiResponse(reqs);
        Api_DEMO_DemoEntity resp = testMock.getResponse();
        Assert.assertEquals(ApiCode.SUCCESS, testMock.getReturnCode());
        Assert.assertEquals(resp.id, 7654321);
        Assert.assertEquals(resp.name, "mock service test NAME");
    }
}
