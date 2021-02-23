package com.nagp.ucp.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagp.ucp.booking.dto.User;
import com.nagp.ucp.common.responses.BaseResponse;

@FeignClient("${UCP_USER_SERVICE_URL:http://ucpuser}")
public interface UserClient {

	@RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
	public BaseResponse<User> getUser(@PathVariable int userId);

	@RequestMapping(method = RequestMethod.GET, value = "/user/provider/{providerId}")
	public BaseResponse<User> getProvider(@PathVariable int providerId);
}
