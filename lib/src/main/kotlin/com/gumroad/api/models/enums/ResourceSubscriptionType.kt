package com.gumroad.api.models.enums

import com.gumroad.api.models.pingbacks.GumroadPing
import com.gumroad.api.models.pingbacks.GumroadResourcePing

enum class ResourceSubscriptionType {
    /**
     * When subscribed to this resource, you will be notified of the
     * user's sales with an HTTP POST to your post_url.
     *
     * The format of the POST is described on the [Gumroad Ping page](https://app.gumroad.com/ping).
     *
     * @see [GumroadPing] for parsing.
     */
    SALE,

    /**
     * When subscribed to this resource, you will be notified of
     * refunds to the user's sales with an HTTP POST to your post_url.
     *
     * The format of the POST is described on the [Gumroad Ping page](https://app.gumroad.com/ping).
     *
     * @see [GumroadPing] for parsing.
     */
    REFUND,

    /**
     * When subscribed to this resource, you will be notified of the
     * disputes raised against user's sales with an HTTP POST to your post_url.
     *
     * The format of the POST is described on the [Gumroad Ping page](https://app.gumroad.com/ping).
     *
     * @see [GumroadPing] for parsing.
     */
    DISPUTE,

    /**
     * When subscribed to this resource, you will be notified of the
     * sale disputes won by the user with an HTTP POST to your post_url.
     *
     * The format of the POST is described on the [Gumroad Ping page](https://app.gumroad.com/ping).
     *
     * @see [GumroadPing] for parsing.
     */
    DISPUTE_WON,

    /**
     * When subscribed to this resource, you will be notified of
     * cancellations of the user's subscribers with an HTTP POST to your post_url.
     *
     * @see [GumroadResourcePing] for parsing.
     */
    CANCELLATION,

    /**
     * When subscribed to this resource, you will be notified when subscriptions
     * to the user's products have been upgraded or downgraded with an HTTP POST to your post_url.
     *
     * A subscription is "upgraded" when the subscriber switches to
     * an equally or more expensive tier and/or subscription duration.
     *
     * It is "downgraded" when the subscriber switches to a less
     * expensive tier and/or subscription duration.
     *
     * In the case of a downgrade, this change will take effect
     * at the end of the current billing period.
     *
     * (Note: This currently applies only to tiered membership products, not to all subscription products.)
     *
     * @see [GumroadResourcePing] for parsing.
     */
    SUBSCRIPTION_UPDATED,

    /**
     * When subscribed to this resource, you will be notified when
     * subscriptions to the user's products have ended with an HTTP POST to your post_url.
     *
     * These events include termination of a subscription due to:
     *
     * - failed payment(s);
     * - cancellation;
     * - or a subscription of fixed duration ending.
     *
     * Notifications are sent at the time the subscription has officially ended, not, for example, at the time cancellation is requested.
     *
     * @see [GumroadResourcePing] for parsing.
     */
    SUBSCRIPTION_ENDED,

    /**
     * When subscribed to this resource, you will be notified
     * when subscriptions to the user's products have been restarted with an HTTP POST to your post_url.
     *
     * A subscription is "restarted" when the subscriber restarts their subscription after previously terminating it.
     *
     * @see [GumroadResourcePing] for parsing.
     */
    SUBSCRIPTION_RESTARTED;

    /**
     * Must be lowercase for the API.
     */
    override fun toString(): String {
        return name.lowercase()
    }
}