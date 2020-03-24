package com.aistar.controller;

import com.aistar.pojo.*;
import com.aistar.service.DetailService;
import com.aistar.service.FlowService;
import com.aistar.service.OrderService;
import com.aistar.service.ProductService;
import com.aistar.util.AlipayConfig;
import com.aistar.util.SeverResponse;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FlowService flowService;


    @PostMapping("/order")
    public ModelAndView createOrder(Integer proId,Integer proNum){
        Orders order = new Orders();
        order.setCustomerId(1);
        order.setOrderCreateTime(new Date());
        order.setOrderPayStatus(0);
        SeverResponse severResponse = productService.getById(proId);
        Pro product = (Pro) severResponse.getData();
        BigDecimal productPrice = product.getProductPrice();
        order.setOrderAmmount(proNum*productPrice.floatValue());
        orderService.save(order);
        order = orderService.getByTime();
        Detail detail = new Detail();
        detail.setOrderId(order.getOrderId());
        detail.setProductId(proId);
        detail.setProductNum(proNum);
        detailService.save(detail);
        SeverResponse severResponse1 = orderService.getById(order.getOrderId());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("order");
        mav.addObject("orderDetail",severResponse1.getData());
        return mav;
    }


    @PostMapping(value = "/alipay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String pay(Integer orderId) throws AlipayApiException {
        SeverResponse severResponse = orderService.getById(orderId);
        OrderDetail orderDetail = (OrderDetail) severResponse.getData();

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset,
                AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);



        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = String.valueOf(orderId);
        //付款金额，必填
        String total_amount = orderDetail.getOrders().getOrderAmmount() +"0";
        //订单名称，必填
        String subject = orderDetail.getPro().getProductName();
        //商品描述，可空
        String body = "product number：" + orderDetail.getDetail().getProductNum();

        System.out.println("out_trade_no:"+out_trade_no);
        System.out.println("total_amount:"+total_amount);
        System.out.println("subject:"+subject);
        System.out.println("body:"+body);

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println("result :"+result);
        return result;

    }

    /**
     *  支付宝同步通知页面
     */
    @RequestMapping(value = "/alipayReturnNotice")
    public ModelAndView alipayReturnNotice(HttpServletRequest request) throws Exception {

        System.out.println("支付成功后，支付宝返回的所有的数据："+ request);
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            System.out.println("return notice name:"+name);
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        System.out.println("params:"+params);
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        System.out.println("return notice signVerified:"+signVerified);
        ModelAndView mv = new ModelAndView("alipaySuccess");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("return out_trade_no:" + out_trade_no);
            System.out.println("return trade_no:" + trade_no);
            System.out.println("return total_amount:" + total_amount);
            //	System.out.println("return productName:"+ product.getName());


            // 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
            SeverResponse severResponse = orderService.getById(Integer.valueOf(out_trade_no));
            OrderDetail orderDetail = (OrderDetail) severResponse.getData();
            Orders order = orderDetail.getOrders();
            order.setOrderPayStatus(1);
            Pro product = orderDetail.getPro();

            Flow flow = new Flow();
            flow.setFlowId(UUID.randomUUID().toString().replace("-",""));
            flow.setFlowNum(trade_no);
            flow.setCreateTime(new Date());
            flow.setPayAmount(Float.valueOf(total_amount));

            flowService.save(flow);


            mv.addObject("out_trade_no", out_trade_no);
            mv.addObject("trade_no", trade_no);
            mv.addObject("total_amount", total_amount);
            mv.addObject("productName", product.getProductName());


        }else{
            System.out.println("支付, 验签失败...");
        }

        return mv;
    }

    /**
     *
     * @Description: 支付宝异步 通知页面
     *
     */
    @RequestMapping(value = "/alipayNotifyNotice")
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request) throws Exception {


        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
                // 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
                SeverResponse severResponse = orderService.getById(Integer.valueOf(out_trade_no));
                OrderDetail orderDetail = (OrderDetail) severResponse.getData();
                Orders order = orderDetail.getOrders();
                order.setOrderPayStatus(1);
                Pro product = orderDetail.getPro();

                Flow flow = new Flow();
                flow.setFlowId(UUID.randomUUID().toString().replace("-",""));
                flow.setFlowNum(trade_no);
                flow.setCreateTime(new Date());
                flow.setPayAmount(Float.valueOf(total_amount));

                flowService.save(flow);

                System.out.println("********************** 支付成功(支付宝异步通知) **********************");
                System.out.println("* 订单号: {}"+ out_trade_no);
                System.out.println("* 支付宝交易号: {}"+trade_no);
                System.out.println("* 实付金额: {}"+ total_amount);
                System.out.println("* 购买产品: {}"+ product.getProductName());
                System.out.println("***************************************************************");
            }
            System.out.println("支付成功...");

        }else {//验证失败
            System.out.println("支付, 验签失败...");
        }

        return "success";
    }



}
