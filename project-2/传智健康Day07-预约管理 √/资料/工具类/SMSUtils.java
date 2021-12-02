package com.itheima.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 短信发送工具类
 */
public class SMSUtils {
	public static final String VALIDATE_CODE = "SMS_169641911";//发送短信验证码
	public static final String ORDER_NOTICE = "SMS_171352187";//体检预约成功通知

	/**
	 * 发送短信验证码
	 * @param phoneNumbers
	 * @param param
	 * @throws ClientException
	 */
	public static void sendShortMessage(String templateCode,String phoneNumbers,String param) throws ClientException{
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fqa5u2yHbESK9eLVjsN", "r3dVpNNKW2nfJelHLnN9FOZviE0ltm");
		IAcsClient client = new DefaultAcsClient(profile);

		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", phoneNumbers);
		request.putQueryParameter("SignName", "传智健康");
		request.putQueryParameter("TemplateCode", templateCode);
		request.putQueryParameter("TemplateParam", "{\"code\":\""+param+"\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}


	//1234
	public static void main(String[] args) throws ClientException {
		SMSUtils.sendShortMessage("SMS_169641911","15688268846","2345");
	}
}
