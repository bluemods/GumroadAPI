package com.gumroad.api.repos

import com.gumroad.api.results.LicenseResult
import retrofit2.Call
import retrofit2.http.*

/**
 * Manages licenses.
 */
interface LicenseRepository {

    /**
     * Verify a license.
     *
     * NOTE: this is the only API call that does not require an access token.
     *
     * @param productId The ID of the product to verify.
     * @param licenseKey The License Key supplied by the customer.
     * @param incrementUseCount If true and the [productId] and [licenseKey] are valid, the use count is incremented by 1.
     */
    @POST("licenses/verify")
    fun verifyLicense(
        @Query("product_id") productId: String,
        @Query("license_key") licenseKey: String,
        @Query("increment_uses_count") incrementUseCount: Boolean = false
    ): Call<LicenseResult>

    /**
     * Enable a license.
     *
     * @param productId The ID of the product.
     * @param licenseKey The license key to enable.
     */
    @PUT("licenses/enable")
    fun enableLicense(
        @Query("product_id") productId: String,
        @Query("license_key") licenseKey: String
    ): Call<LicenseResult>

    /**
     * Disable a license.
     *
     * @param productId The ID of the product.
     * @param licenseKey The license key to disable.
     */
    @PUT("licenses/disable")
    fun disableLicense(
        @Query("product_id") productId: String,
        @Query("license_key") licenseKey: String
    ): Call<LicenseResult>

    /**
     * Decrement the uses count of a license by 1.
     *
     * @param productId The ID of the product.
     * @param licenseKey The license key to decrement the use count of.
     */
    @PUT("licenses/decrement_uses_count")
    fun decrementLicenseUseCount(
        @Query("product_id") productId: String,
        @Query("license_key") licenseKey: String
    ): Call<LicenseResult>
}