# GumroadAPI
A Kotlin client for the [Gumroad API](https://app.gumroad.com/api)

## Adding as a dependency (Gradle)
Add JitPack first:

```groovy
repositories {
    maven {
        url 'https://jitpack.io'
    }
}
```

Then add the repository:
```groovy
implementation 'com.github.bluemods:GumroadAPI:1.0.3'
```

## Getting an access token (API key)
- Visit the [OAuth application dashboard](https://app.gumroad.com/oauth/applications/)
- Create an application. Redirect URI can be set to `http://127.0.0.1` if you are only using this API.
- Click "Generate Access Token"
- Pass the token to ```Gumroad.create()```

## Example (License verification)

```kotlin
import com.gumroad.api.Gumroad

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

// Example (non-async)
val response = api.verifyLicense(productId, licenseKey).execute()

if (response.isSuccessful) {
  // API call was successful, get the result from Retrofit
  val license = response.body()
  if (license!!.success) {
    // License key exists and matches the product
    // Do what you need to do with the license info.
    // You should probably check for things like chargebacks, refunds, etc.
    // before granting access to a resource.
    println("Found license: $license")
  } else {
    // License does not exist
    println("License does not exist")
  }
} else {
    println("License verification failed: " + response.errorBody())
}
```

More examples can be found [here](/examples).
