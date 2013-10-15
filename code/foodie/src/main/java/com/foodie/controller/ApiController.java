package com.foodie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodie.config.ApplicationHelper;
import com.foodie.model.Location;
import com.foodie.model.LoginInfo;
import com.foodie.model.Menu;
import com.foodie.model.MenuItem;
import com.foodie.model.Order;
import com.foodie.model.PaymentInfo;
import com.foodie.model.People;
import com.foodie.model.Restaurant;
import com.foodie.model.request.CreateNewUserRequest;
import com.foodie.service.AddressService;
import com.foodie.service.DataStoreSetup;
import com.foodie.service.NameListService;
import com.foodie.service.OrderService;
import com.foodie.service.PublicAccessService;
import com.foodie.service.SessionService;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
@RequestMapping(value = ApplicationHelper.CST_API_PATH)
public class ApiController {

    @Autowired
    NameListService nameService;

    @Autowired
    OrderService orderService;
    @Autowired
    PublicAccessService publicAccessService;

    @Autowired
    AddressService addressService;

    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/order/setOrder", method = RequestMethod.POST)
    @ResponseBody
    public QueryResult<String> setOrder(@RequestParam String sessionId, @RequestBody Order order) {
        return QueryResult.createFromSuccess(orderService.setOrder(sessionId, order));
    }
    
    @RequestMapping(value = "/paymentInfo/setPaymentInfo", method = RequestMethod.POST)
    @ResponseBody
    public QueryResult<String> setPaymentInfo(@RequestParam String sessionId,@RequestBody PaymentInfo paymentInfo) {
        return QueryResult.createFromSuccess(orderService.setPaymentInfo(sessionId, paymentInfo));
    }
    @SuppressWarnings("unchecked")
    @RequestMapping(value = ApplicationHelper.CST_API_GET_NAME_LIST, method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<List<People>> getNameList() {
        try {
            List<People> result = nameService.getAllNames();
            return QueryResult.createFromSuccess(result);
        } catch (Exception e) {
            return (QueryResult<List<People>>) QueryResult.createFromFailure(e.getMessage());
        }

    }
    
    @RequestMapping(value = "/test/utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String testUTF_8() {
        return "中文字符";

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = ApplicationHelper.CST_API_GET_RESTAURANT_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<Restaurant> getRestaurantById(@RequestParam("restaurantId") String restaurantId) {
        try {
            Restaurant result = publicAccessService.getRestaurantById(KeyFactory.stringToKey(restaurantId));
            return (QueryResult<Restaurant>) QueryResult.createFromSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
            return (QueryResult<Restaurant>) QueryResult.createFromFailure(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = ApplicationHelper.CST_API_GET_MENU, method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<List<Menu>> getMenu(@RequestParam("restaurantId") String restaurantId) {
        try {
            List<Menu> result = publicAccessService.getMenu(KeyFactory.stringToKey(restaurantId));
            return QueryResult.createFromSuccess(result);
        } catch (Exception e) {
            return (QueryResult<List<Menu>>) QueryResult.createFromFailure(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = ApplicationHelper.CST_API_GET_MENU_ITEM, method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<List<MenuItem>> getMenuItem(@RequestParam("menuId") String menuId) {
        try {
            List<MenuItem> result = publicAccessService.getMenuItem(KeyFactory.stringToKey(menuId));
            return QueryResult.createFromSuccess(result);
        } catch (Exception e) {
            return (QueryResult<List<MenuItem>>) QueryResult.createFromFailure(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = ApplicationHelper.CST_API_GET_MENU_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<Menu> getMenuById(@RequestParam("menuId") String menuId, HttpServletResponse resq) {
        try {
            Menu result = publicAccessService.getMenuById(KeyFactory.stringToKey(menuId));
            return (QueryResult<Menu>) QueryResult.createFromSuccess(result);
        } catch (Exception e) {
            return (QueryResult<Menu>) QueryResult.createFromFailure(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = ApplicationHelper.CST_API_GET_MENU_ITEM_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<MenuItem> getMenuItemById(@RequestParam("menuItemId") String menuItemId) {
        try {
            MenuItem result = publicAccessService.getMenuItemById(KeyFactory.stringToKey(menuItemId));
            return (QueryResult<MenuItem>) QueryResult.createFromSuccess(result);
        } catch (Exception e) {
            return (QueryResult<MenuItem>) QueryResult.createFromFailure(e.getMessage());
        }

    }

    @RequestMapping(value = ApplicationHelper.CST_API_OPEN_SESSION, method = RequestMethod.GET)
    @ResponseBody
    public void openSession(HttpServletResponse resp) throws IOException {
        Cookie testCookie = new Cookie(ApplicationHelper.CST_TEST_COOKIE_NAME, ApplicationHelper.CST_TEST_COOKIE_VALUE);
        resp.addCookie(testCookie);
        resp.sendRedirect(ApplicationHelper.CST_API_PATH + ApplicationHelper.CST_API_GENERATE_SESSION_ID);
    }
    
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public QueryResult<Boolean> login(@RequestParam String sessionId, @RequestBody LoginInfo loginInfo) {
        return QueryResult.createFromSuccess(publicAccessService.login(sessionId, loginInfo));
    }
    
    @RequestMapping(value = "/user/createNewUser", method = RequestMethod.POST)
    @ResponseBody
    public QueryResult<String> createNewUser(@RequestBody CreateNewUserRequest request) {
        return QueryResult.createFromSuccess(publicAccessService.createNewUser(request.getSessionId(), request.getUser(), request.getLocation()));
    }
    
    @RequestMapping(value = ApplicationHelper.CST_API_ADDRESS_SET_ADDRESS, method = RequestMethod.POST)
    @ResponseBody
    public QueryResult<String> setAddress(@RequestParam String sessionId, @RequestBody Location address) {
        addressService.setAddress(sessionId, address);
        return QueryResult.createFromSuccess("success");
    }
    @RequestMapping(value = ApplicationHelper.CST_API_ADDRESS_GET_BY_USER_ID + "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<List<Location>> getAddressByUserId(@PathVariable String userId) {
        List<Location> data = addressService.getAddressByUserId(userId);
        return QueryResult.createFromSuccess(data);
        
    }
    
    @RequestMapping(value = ApplicationHelper.CST_API_ADDRESS_GET_BY_RESTAURANT_ID + "/{restaurantId}", method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<List<Location>> getAddressByRestaurantId(@PathVariable String restaurantId) {
        List<Location> data = addressService.getAddressByRestaurantId(restaurantId);
        return QueryResult.createFromSuccess(data);
        
    }
    @RequestMapping(value = ApplicationHelper.CST_API_ADDRESS_GET_ADDRESS_BY_ID + "/{addressId}", method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<Location> getAddressById(@PathVariable String addressId) {
        Location data = addressService.getAddressById(addressId);
        return QueryResult.createFromSuccess(data);
        
    }
//    getAddressByRestaurantId
//    getAddressById

    @SuppressWarnings("unchecked")
    @RequestMapping(value = ApplicationHelper.CST_API_GENERATE_SESSION_ID, method = RequestMethod.GET)
    @ResponseBody
    public QueryResult<String> generateSessionId(HttpServletRequest req, HttpServletResponse resp) {
        QueryResult<String> result = null;

        // At moment there is no exception thrown
        try {
            Boolean cookieSupport = sessionService.checkCookieSupport(req);
            String sessionId = sessionService.generateSessionId(req, resp, cookieSupport);

            result = (QueryResult<String>) QueryResult.createFromSuccess(sessionId);
            result.setMessage(ApplicationHelper.CST_COOKIE_SUPPORT + cookieSupport);

        } catch (Exception e) {
            result = (QueryResult<String>) QueryResult.createFromFailure(e.toString());
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/setMockNames", method = RequestMethod.GET)
    @ResponseBody
    public Result setMockNames() {
        try {
            DataStoreSetup.setup();
            return Result.CreateFromSuccess(true);
        } catch (Exception e) {
            return Result.CreateFromFailure(e);
        }

    }
}
