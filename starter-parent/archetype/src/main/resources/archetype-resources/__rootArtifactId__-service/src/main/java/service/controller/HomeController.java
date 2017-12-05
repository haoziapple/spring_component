#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.controller;

import ${package}.intf.bean.common.ReqBean;
import ${package}.intf.bean.common.ResultBean;
import ${package}.intf.bean.order.SubmitOrderInfo;
import ${package}.intf.bean.order.SubmitOrderRsp;
import ${package}.service.common.aop.AddLog;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/11/16.
 */
@Api("主页api")
@RestController
@RequestMapping("/")
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @AddLog
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResultBean<String> home(Locale locale) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        return new ResultBean<>(formattedDate);
    }

    @AddLog
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ResultBean error() {
        return new ResultBean(new RuntimeException("This is a test exception"));
    }

    @AddLog
    @RequestMapping(value = "/testModel", method = RequestMethod.GET)
    public ResultBean<SubmitOrderRsp> testSwaggerModel() {
        SubmitOrderRsp rsp = new SubmitOrderRsp();
        return new ResultBean<>(rsp);
    }

    @AddLog
    @RequestMapping(value = "/testValid", method = RequestMethod.POST)
    public String testValid(@RequestBody @Valid ReqBean req, BindingResult bindingResult)
    {
        return "success";
    }

    @AddLog
    @RequestMapping(value = "/testThrow", method = RequestMethod.GET)
    public String testThrow()
    {
        throw new RuntimeException("test throw");
//        return "success";
    }

}
