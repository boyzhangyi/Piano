package com.shumei.piano.bus.json.juhe;

/**
 * 聚合数据错误码
 *
 * @author Eden Zhang 2018/9/23
 * @version Piano V1.0.1
 */
public enum ErrorCode
{
    ERROR_10001(10001, "错误的请求KEY"),

    ERROR_10002(10002, "该KEY无请求权限"),

    ERROR_10003(10003, "KEY过期"),

    ERROR_10004(10004, "错误的OPENID"),

    ERROR_10005(10005, "应用未审核超时，请提交认证"),

    ERROR_10007(10007, "未知的请求源"),

    ERROR_10008(10008, "被禁止的IP"),

    ERROR_10009(10009, "被禁止的KEY"),

    ERROR_10011(10011, "当前IP请求超过限制"),

    ERROR_10012(10012, "请求超过次数限制"),

    ERROR_10013(10013, "测试KEY超过请求限制"),

    ERROR_10014(10014, "系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)"),

    ERROR_10020(10020, "接口维护"),

    ERROR_10021(10021, "接口停用");

    private int code;

    private String reason;

    ErrorCode(int code, String reason)
    {
        this.code = code;
        this.reason = reason;
    }

    public int getCode()
    {
        return code;
    }

    public String getReason()
    {
        return reason;
    }
}
