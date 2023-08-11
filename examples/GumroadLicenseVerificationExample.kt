package examples

import com.gumroad.api.Gumroad
import retrofit2.Call

object GumroadLicenseVerificationExample {

    @JvmStatic
    fun main(args: Array<String>) {
        // Create an instance of the Gumroad API client.
        // If you are only using the verifyLicense endpoint, null can be used here
        val api = Gumroad.create("YOUR_API_KEY_HERE")

        // Verify a Gumroad license key for a product.
        // Product IDs can be found
        // - Go to https://app.gumroad.com/products/,
        // - Click on the product name to edit it
        // - Scroll down to Settings
        // - Enable "Generate a unique license key per sale" if not enabled already
        // - Copy the "product_id" text field.
        val productId = "YOUR_PRODUCT_ID_HERE"

        // License keys are generated for each sale while "Generate a unique license key per sale" is enabled for a product.
        // Get them from the sales lists or when provided by a customer
        val licenseKey = "YOUR_LICENSE_KEY_HERE"

        // Example (async)
        api.verifyLicense(productId = productId, licenseKey = licenseKey).sendAsync({ license ->
            if (license.success) {
                // License key exists and matches the product
                // Do what you need to do with the license info.
                // You should probably check for things like chargebacks, refunds, etc.
                // before granting access to a resource.
                println("Found license: $license")
            } else {
                // License does not exist
                println("License does not exist")
            }
        }, { error ->
            // A network or API error occurred.
            error.printStackTrace()
        })

        // Example (non-async)
        val response = api.verifyLicense(productId = productId, licenseKey = licenseKey).execute()
        if (response.isSuccessful) {
            // API call was successful, get the result from Retrofit
            val license = response.body()
            if (license!!.success) {
                // License key exists and matches the product
                // Do what you need to do with the license info.
                println("Found license: $license")
            } else {
                // License does not exist
                println("License does not exist")
            }
        } else {
            println("License verification failed: " + response.errorBody())
        }
    }

    // Simple extension function to bridge Retrofit callbacks into lambdas
    private fun <T> Call<T>.sendAsync(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit = {}) {
        enqueue(object : retrofit2.Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                if (response.isSuccessful) {
                    onSuccess.invoke(response.body()!!)
                } else {
                    response.errorBody()!!.use { onError.invoke(Exception("URL: ${call.request().url}, body: ${it.string()}")) }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onError.invoke(t)
            }
        })
    }
}