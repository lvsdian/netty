package cn.andios.test;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * @description:
 * @author:LSD
 * @when:2020/04/26/18:33
 */
public class Test1 {
    public static void main(String[] args) {
        int pro = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(NettyRuntime.availableProcessors());
        System.out.println(pro);
    }
}
