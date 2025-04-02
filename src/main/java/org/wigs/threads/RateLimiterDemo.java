package org.wigs.threads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem:
 * Design a rate limiter that allows only N requests per second per user.
 */
class RateLimiter {
    private final int maxRequests;
    private final ConcurrentHashMap<String, AtomicInteger> userRequests = new ConcurrentHashMap<>();

    public RateLimiter(int maxRequests) {
        this.maxRequests = maxRequests;
    }

    public boolean allowRequest(String userId) {
        userRequests.putIfAbsent(userId, new AtomicInteger(0));
        if (userRequests.get(userId).incrementAndGet() > maxRequests) {
            return false;
        }
        return true;
    }
}

public class RateLimiterDemo {
    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter(5);

        for (int i = 1; i <= 10; i++) {
            String user = "User1";
            if (limiter.allowRequest(user)) {
                System.out.println("Request allowed for " + user);
            } else {
                System.out.println("Request denied for " + user);
            }
        }
    }
}

