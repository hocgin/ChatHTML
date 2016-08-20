package in.hocg.chat.router.core;


import in.hocg.chat.router.pkg.ReceiveMessage;
import in.hocg.chat.router.pkg.ReplyMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * (๑`灬´๑)
 * Author: hocgin(http://hocg.in)
 * GitHub: https://github.com/hocgin
 * --------------------
 * Created 16-7-16.
 * 消息路由
 */
public class MessageRouter {
    private static final int DEFAULT_THREAD_POOL_SIZE = 100;
    private final List<MessageRouterRule> rules = new ArrayList<MessageRouterRule>();
    private ExecutorService executorService;

    public MessageRouter() {
        executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
    }

    public MessageRouterRule rule() {
        return new MessageRouterRule(this);
    }

    protected List<MessageRouterRule> getRules() {
        return this.rules;
    }


    /**
     * 消息处理器
     *
     * @param message
     * @return
     */
    public ReplyMessage route(ReceiveMessage message) {
        final List<MessageRouterRule> matchRules = new ArrayList<>();
        for (final MessageRouterRule rule : rules) { // 收集匹配规则
            if (rule.test(message)) {
                matchRules.add(rule);
                if (!rule.isReEnter()) {
                    break;
                }
            }
        }

        if (matchRules.size() == 0) {
            return null;
        }
        ReplyMessage re = null;
        final List<Future> futures = new ArrayList<>();
        for (final MessageRouterRule matchRule : matchRules) { // 处理器处理匹配消息
            if (matchRule.isAsync()) {
                futures.add(executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        matchRule.service(message);
                    }
                }));
            } else {
                re = matchRule.service(message);
            }
        }

        if (futures.size() > 0) { // 异常信息
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (Future future : futures) {
                        try {
                            future.get();
                        } catch (Exception e) {
                            // todo 异常记录/处理
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        return re;
    }
}
