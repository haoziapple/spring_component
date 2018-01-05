package com.fzrj.starter.service.sandbox.excelexport;

/**
 * Created by Administrator on 2018/1/5 0005.
 */
public enum ErrorCode {
    Success(0,"操作成功"),
    Failure(1,"操作失败"),
    NeedLogin(2,"用户没有登录"),
    UnExceptedError(3,"未知的错误发生"),
    IllegalArument(4,"参数错误"),
    SQLIntegrityConstraintViolation(5,"违反完整性约束"),
    NoAuthorization(6,"没有权限执行此操作"),
    CheckLoginFailure(7,"用户不存在或者密码错误"),
    UserNameExists(8,"登录名称已存在"),
    RoleNameExists(9,"角色名已存在"),
    RoleIsUsed(10,"角色已经被使用"),
    UserLocked(11,"用户被锁定"),
    OldPwdNotRight(12,"原密码不正确"),
    NewPwdNotMatchConfirmPwd(13,"新密码与确认密码不一致"),
    SqlSyntaxError(14,"数据库执行异常"),
    UserDisable(15,"无效用户"),
    /**----------------------------------------------------------------
     * 线以上内容请不要修改
     * ----------------------------------------------------------------**/
    YourErrorCodeGoesHere(1000,"你的失败码请在后面定义"),
    //基础模块：1000
    GenNoCreateFailed(1000+2,"自动单号生成失败"),
    ParameterInError(1000+3,"入参错误"),
    InsertFailed(1000+4,"新增失败"),
    UpdateFailed(1000+5,"修改失败"),
    DeleteFailed(1000+6,"删除失败"),
    FileTypeInValid(1000+7,"文件格式不正确"),
    FileWriteFalid(1000+8,"文件写入失败"),
    FileWrite2WoorkbookFailed(1000+9,"从文件写入工作溥失败"),
    ExcelMoudleNotExist(1000+10,"Excel模板不存在"),
    PartlyFailed(1000+11,"部分操作失败"),
    OperateLaunchManTypeNotExist(1000+12,"操作发起人类型系统中不存在"),


    //商品服务：2000
    ProductStatusError(2000+1,"状态切换错误"),
    ProductInfoNotExist(2000+2,"商品信息不存在"),
    IntegralPayCantWithOtherPayWay(2000+3,"积分支付不能与其他支付方式同时使用"),
    CashPayOnlyUseOnce(2000+4,"现金支付方式只能使用一种"),
    ProductSaleInfoNotExist(2000+5,"商品的销售信息不存在"),
    ProductSpecNotExist(2000+6,"商品对应的规格信息不存在"),
    ProductSpecInfoMiss(2000+6,"商品对应的规格部分信息缺失"),
    ProductMerchantInfoNotExist(2000+7,"商品对应的商户信息不存在"),
    ProductSpecLeak(2000+8,"缺少规格信息"),
    ProductStatusInValid(2000+9,"商品状态非法"),
    ProductOrdered(2000+10,"商品已生成了订单"),
    ProductSave(2000+11,"商品保存失败"),
    ProductSubmit(2000+12,"此状态下的商品不允许提交操作"),
    ProductRevoke(2000+13,"此状态下的商品不允许撤销操作"),
    ProductAuditPass(2000+14,"此状态下的商品不允许通过操作"),
    ProductAuditRefused(2000+15,"此状态下的商品不允许拒绝操作"),
    ProductUpShelve(2000+16,"此状态下的商品不允许上架操作"),
    ProductUnShelve(2000+17,"此状态下的商品不允许下架操作"),
    ProductClose(2000+18,"此状态下的商品不允许关闭操作"),
    ProductDelete(2000+19,"此状态下的商品不允许删除操作"),
    ProductInvtReduceTypeNotExist(2000+11,"商品的库存扣减方式不存在"),
    ProductInvtReduceTypeInValid(2000+12,"商品的库存扣减方式无效"),
    ProductInvtNotEnough(2000+13,"商品库存不足，扣减库存失败"),

    //商品服务—分类：2200
    ProductClassNameExist(2200+1,"同一分类下同级分类名称已存在"),
    ProductClassCodeExist(2200+2,"分类编码已存在"),

    //商品服务—单位：2300
    ProductUomNameExist(2300+1,"单位名称已存在"),
    ProductUomReferenced(2300+2,"单位已被商品绑定"),

    ProductBasicInfoNameExist(2400+1,"产品名称已存在"),
    ProductBasicInfoUpdateNotPermit(2400+2,"此状态下的产品不允许修改"),
    productPeriodNameExist(2500+1,"规格名称已存在"),
    productPeriodReferenced(2500+2,"规格已被商品绑定"),

    //订单模块：3000
    OrderNotExist(3000+1,"订单不存在"),
    OrderActionInValid(3000+2,"订单操作不存在"),
    OrderDtlNotIn(3000+3,"订单中的商品明细不存在"),
    OrderPayWayNotIn(3000+4,"订单中的支付明细不存在"),

    //订单模块—下单操作：3100
    OrderPayWayNotExist(3100+1,"订单所选支付方式系统中不存在"),
    OrderNoRepeat(3100+10,"订单号已存在，不能重复下单"),
    OrderPayWayCantThanTwo(3100+3,"订单所选支付方式不能超过两种"),
    OrderNotChoosePayWay(3100+4,"下单未选择支付方式"),
    OrderPayInfoExistNullValue(3100+5,"下单传入的支付信息存在空值"),
    OrderNotChooseProduct(3100+6,"下单未选择商品"),
    OrderNotInputQuantity(3100+7,"下单未传入所选商品数量"),
    OrderAccountInsertFailed(3100+8,"下单账户新增失败"),
    OrderChooseProductNotInSale(3100+9,"下单所选商品不在销售中"),

    //订单模块—订单状态：3200
    OrderStateChangeFailed(3200+1,"订单状态切换失败"),
    OrderStatusNotExist(3200+2,"订单状态不存在"),
    OrderStateNotPayFailedCantClose(3200+3,"订单状态非支付失败，不能关闭订单"),
    OrderPayUnTimeOut(3200+4,"订单支付未到截止时间，不能关闭订单"),
    CurrOrderStatusCantClose(3200+5,"当前订单状态，不能关闭订单"),
    OrderStateNotPayOrInvtReduceCantRefund(3200+6,"订单状态非支付失败或库存扣减失败，不能进行退款"),
    OperateLaunchManTypeCantRefund(3200+7,"当前操作发起人类型不能进行退款操作"),
    OperateLaunchManTypeCantPay(3200+8,"当前操作发起人类型不能进行支付操作"),
    OrderCurrStateCantPay(3200+9,"订单状态当前状态，不能进行支付"),
    OrderStateNotPayFailedCantSetPayed(3200+10,"订单状态非支付失败，不能设置已支付"),
    OperateLaunchManTypeCantSetPayed(3200+11,"当前操作发起人类型不能进行设置已支付操作"),
    OrderStateNotDeliveFailedCantSetDelived(3200+12,"订单状态非发货失败，不能设置已发货"),
    OperateLaunchManTypeCantSetDelived(3200+13,"当前操作发起人类型不能进行设置已发货操作"),

    //订单模块—订单设置：3300
    OrderSettingSaveFailed(3300+1,"订单设置保存失败"),

    //订单模块—订单支付退款：3400
    RefundCantThanPayedAmount(3400+1,"订单退款不能大于已支付金额"),
    RefundWayNotUsedInOrder(3400+2,"选择的退款方式未在订单中进行支付"),
    WxRefundCantThanOneYeay(3400+3,"微信退款，不支持超过一年的订单"),
    WxRefundCantThan50(3400+4,"微信退款，单笔订单,不支持超过50次的部分退款"),

    //用户体系模块：4000
    DepartmentNameExist(4000+19,"名称已存在"),
    DepartmentHasSubsidiaryDepartment(4000+20, "部门下存在子部门"),
    DepartmentHasUsers(4000+21, "部门下存在人员"),

    //商户模块：5000
    MerchantNameExist(500+1,"商户名称已存在"),
    MerchantCodeExist(5000+2,"商户编号已存在"),
    MerchantReferenced(5000+3,"商户已被商品绑定"),

    ;


    private String desc;
    private int code;

    ErrorCode(int code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc()
    {
        return desc;
    }

    public int getCode()
    {
        return code;
    }
}
