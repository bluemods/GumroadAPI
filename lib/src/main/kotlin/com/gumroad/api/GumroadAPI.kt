package com.gumroad.api

import com.gumroad.api.repos.*

/**
 * Base interface for interacting with the Gumroad API.
 */
interface GumroadAPI :
    LicenseRepository,
    ProductRepository,
    ProductVariantRepository,
    ProductVariantCategoryRepository,
    OfferCodeRepository,
    CustomFieldRepository,
    ResourceSubscriptionRepository,
    SaleRepository,
    SubscriberRepository,
    UserRepository