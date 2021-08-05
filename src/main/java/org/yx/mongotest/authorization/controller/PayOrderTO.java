package org.yx.mongotest.authorization.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author :admin
 * @date : 2021/6/30 17:50
 * @see <a href="https://open.lianlianpay.com/apis/unified-payment.html">统一支付创单API</a>
 */
@Data
@Accessors(chain = true)
public class PayOrderTO {

    @JsonProperty("api_version")
    private String apiVersion;

    @JsonProperty("sign")
    private String sign;

    @JsonProperty("sign_type")
    private String signType;

    /**
     * yyyyMMddHHmmss HH以24小时为准，如20170309143712。当time_stamp 与连连服务器的时间(北京时间)之间的误差超过30分钟时， 该次请求将返回交易已过期。
     */
    @JsonProperty("time_stamp")
    private String timeStamp;

    /**
     * 商户编号是商户在连连支付支付平台上开设的商户号码，为18位数字，如：201304121000001004。测试阶段可以先用测试商户号测试。
     *
     * @see <a href="https://open.lianlianpay.com/docs/resources/sandbox-account.html"></a>
     */
    @JsonProperty("oid_partner")
    private String oidPartner;

    /**
     * 用户编号。 商户系统内对用户的唯一编码，可以为自定义字符串，加密密文或者邮箱等可以唯一定义用户的标识。
     */
    @JsonProperty("user_id")
    private String userId;

    /**
     * 虚拟商品销售：101001。
     * 实物商品销售：109001。当busi_partner与您的商户号的业务属性不相符时， 该次请求将返回请求无效。
     */
    @JsonProperty("busi_partner")
    private String busiPartner;

    /**
     * 商户订单号。 为商户系统内对订单的唯一编号，保证唯一。 连连会根据no_order 创建连连订单号 oid_paybill，
     * 如no_order已有对应连连订单号 oid_paybill，则将请求视为重复订单请求。 重复发起订单请求时， 请求中的参数信息需与原创单时一致。
     */
    @JsonProperty("no_order")
    private String noOrder;

    /**
     * 商户订单时间。格式为yyyyMMddHHmmss， HH以24小时为准，如 20180130161010。
     */
    @JsonProperty("dt_order")
    private String dtOrder;

    @JsonProperty("name_goods")
    private String nameGoods;

    @JsonProperty("info_order")
    private String infoOrder;

    /**
     * 交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999。初始额度：50元，
     */
    @JsonProperty("money_order")
    private String moneyOrder;

    /**
     * 接收异步通知的线上地址。连连支付支付平台在用户支付成功后通知商户服务端的地址。如 http://test.lianlianpay.com.cn/help/notify.php 。异步通知处理规则及详情见异步通知。
     */
    @JsonProperty("notify_url")
    private String notifyUrl;

    @JsonProperty("valid_order")
    private int validOrder;

    @JsonProperty("risk_item")
    private String riskItem;

    @JsonProperty("shareing_data")
    private String shareingData;

    /**
     * 支付结束后，连连会将消费者重定向至此地址。
     */
    @JsonProperty("url_return")
    private String urlReturn;

    /**
     * 传入后在收银台页面激活返回按钮， 用户点击返回后跳转向该地址(POST)。 如不传则返回按钮不显示。
     */
    @JsonProperty("back_url")
    private String backUrl;

    /**
     * 支付产品标识。
     * 0， 快捷收款。
     * 1， 认证收款。
     * 2， 网银收款。
     * 5， 新认证收款。
     * 12， 手机网银收款 。
     */
    @JsonProperty("flag_pay_product")
    private String flagPayProduct;

    /**
     * 应用渠道标识。
     * 0， App-Android。
     * 1， App-iOS。
     * 2， Web。
     * 3， H5。
     * 注意:
     * 当flag_pay_product为12（即手机银行APP支付时）, flag_chnl仅支持传入0或1。
     */
    @JsonProperty("flag_chnl")
    private String flagChnl;

    /**
     * 用户姓名，为用户在银行预留的姓名信息，中文。 对于少数民族，间隔号以「·」（U+00B7 MIDDLE DOT）为准。
     * 注意
     * 使用支付产品认证收款及新认证收款时acct_name为必传字段。历次支付时，传入no_agree则acct_name为可选参数， 两者都传时， 以no_agree为准。
     */
    @JsonProperty("acct_name")
    private String acctName;

    /**
     * 证件类型。
     * 0， 身份证。
     * 1， 户口簿。
     * 2， 护照。
     * 3， 军官证。
     * 4， 士兵证。
     * 5， 港澳居民来往内地通行证。
     * 6，台湾同胞来往内地通行证。
     * 7， 临时身份证
     * 8，外国人居住证。
     * 9，警官证。
     * 10，组织机构代码
     * X， 其他证件。
     * 注意
     * 使用支付产品认证收款及新认证收款时id_type为必传字段。
     * 默认为0， 且目前仅支持0。 历次支付时，传入no_agree则id_type为可选参数， 两者都传时， 以no_agree为准。
     */
    @JsonProperty("id_type")
    private String idType;

    /**
     * 证件号码。 对应id_type的相关证件号码。
     * 注意
     * 使用支付产品认证收款及新认证收款时id_no为必传字段。历次支付时，传入no_agree则id_no为可选参数， 两者都传时， 以no_agree为准。
     */
    @JsonProperty("id_no")
    private String idNo;


    @JsonProperty("day_billvalid")
    private String dayBillvalid;

}
