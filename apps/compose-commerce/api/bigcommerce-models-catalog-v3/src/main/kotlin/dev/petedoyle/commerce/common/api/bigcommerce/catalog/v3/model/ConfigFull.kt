/**
 * Catalog
 *
 * Manage products, brands and categories. To learn more about catalog resources see [Catalog Overview](https://developer.bigcommerce.com/api-docs/catalog/products-overview).  - [Authentication](#authentication) - [Differentiating Variants and Modifiers](#differentiating-variants-and-modifiers) - [Available Endpoints](#available-endpoints) - [Resources](#resources)  ## Authentication Requests can be authenticated by sending an `access_token` via `X-Auth-Token` HTTP header:  ```http GET /stores/{$$.env.store_hash}/v3/catalog/summary host: api.bigcommerce.com Accept: application/json X-Auth-Token: {access_token} ```  |Header|Parameter|Description| |-|-|-| |`X-Auth-Token`|`access_token `|Obtained by creating an API account or installing an app in a BigCommerce control panel.|  ### OAuth Scopes | UI Name  | Permission | Parameter                     | |----------|------------|-------------------------------| | Products | modify     | `store_v2_products`           | | Products | read-only  | `store_v2_products_read_only` |  For more information on OAuth Scopes, see: [Authentication](https://developer.bigcommerce.com/api-docs/getting-started/authentication).  For more information on Authenticating BigCommerce APIs, see: [Authentication](https://developer.bigcommerce.com/api-docs/getting-started/authentication).  ## Differentiating Variants and Modifiers [Variants](https://support.bigcommerce.com/s/article/Product-Options-v3#variations) represent a physical product made up of [Product Option](https://support.bigcommerce.com/s/article/Product-Options-v3) choices, i.e. a large blue t-shirt. Each variant can have a unique SKU.  Modifiers represent a choice a customer makes about a product that doesn't represent a physical item, i.e. text to be printed on a t-shirt. Assigning a SKU to a modifier will turn it into a variant.  See [Variant Options](https://developer.bigcommerce.com/api-docs/catalog/products-overview#variant-options) and [Modifier Options](https://developer.bigcommerce.com/api-docs/catalog/products-overview#modifier-options) for more information.  ## Available Endpoints | Resource / Endpoint                     | Description                                                             | |-----------------------------------------|-------------------------------------------------------------------------| | Brand Images                            | Create and manage brand images                                          | | Brand Metafields                        | Create and manage brand metafields                                      | | Brands                                  | Create and manage brands                                                | | Catalog                                 | Create and manage store categories, products, and brands                | | Categories                              | Create and manage categorties                                           | | Category Images                         | Create and manage category images                                       | | Category Metafields                     | Create and manage category metafields                                   | | Product Bulk Pricing Rules              | Create and manage product bulk pricing rules                            | | Product Complex Rules                   | Create and manage product complex rules                                 | | Product Custom Fields                   | Create and manage product custom fields                                 | | Product Images                          | Create and manage product images                                        | | Product Metafields                      | Create and manage product meta fields                                   | | Product Modifier Images                 | Create and manage product modifer images                                | | Product Modifier Values                 | Create and manage product modifier values                               | | Product Modifiers                       | Create and manage product midifiers                                     | | Product Reviews                         | Create and manage product reviews                                       | | Product Variant Option Values           | Create and manage product variant option values                         | | Product Variant Options                 | Create and manage product variant options                               | | Product Variants                        | Create and manage product variants                                      | | Product Videos                          | Create and manage product videos                                        | | Products                                | Create and manage products                                              | | ProductVariant Metafields               | Create and manage product meta flields                                  | | Variants                                | Get and update all variants                                             |  ## Resources  ### Webhooks * [Products](/api-docs/store-management/webhooks/events#products) * [Categories](/api-docs/store-management/webhooks/events#category) * [SKU](/api-docs/store-management/webhooks/events#sku)
 *
 * The version of the OpenAPI document: 3.0-noerrors
 * 
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The values for option config can vary based on the Modifier created.
 *
 * @param defaultValue (date, text, multi_line_text, numbers_only_text) The default value. Shown on a date option as an ISO-8601–formatted string, or on a text option as a string. 
 * @param checkedByDefault (checkbox) Flag for setting the checkbox to be checked by default. 
 * @param checkboxLabel (checkbox) Label displayed for the checkbox option. 
 * @param dateLimited (date) Flag to limit the dates allowed to be entered on a date option. 
 * @param dateLimitMode (date) The type of limit that is allowed to be entered on a date option. 
 * @param dateEarliestValue (date) The earliest date allowed to be entered on the date option, as an ISO-8601 formatted string. 
 * @param dateLatestValue (date) The latest date allowed to be entered on the date option, as an ISO-8601 formatted string. 
 * @param fileTypesMode (file) The kind of restriction on the file types that can be uploaded with a file upload option. Values: `specific` - restricts uploads to particular file types; `all` - allows all file types. 
 * @param fileTypesSupported (file) The type of files allowed to be uploaded if the `file_type_option` is set to `specific`. Values:   `images` - Allows upload of image MIME types (`bmp`, `gif`, `jpg`, `jpeg`, `jpe`, `jif`, `jfif`, `jfi`, `png`, `wbmp`, `xbm`, `tiff`). `documents` - Allows upload of document MIME types (`txt`, `pdf`, `rtf`, `doc`, `docx`, `xls`, `xlsx`, `accdb`, `mdb`, `one`, `pps`, `ppsx`, `ppt`, `pptx`, `pub`, `odt`, `ods`, `odp`, `odg`, `odf`).   `other` - Allows file types defined in the `file_types_other` array. 
 * @param fileTypesOther (file) A list of other file types allowed with the file upload option. 
 * @param fileMaxSize (file) The maximum size for a file that can be used with the file upload option. This will still be limited by the server. 
 * @param textCharactersLimited (text, multi_line_text) Flag to validate the length of a text or multi-line text input. 
 * @param textMinLength (text, multi_line_text) The minimum length allowed for a text or multi-line text option. 
 * @param textMaxLength (text, multi_line_text) The maximum length allowed for a text or multi line text option. 
 * @param textLinesLimited (multi_line_text) Flag to validate the maximum number of lines allowed on a multi-line text input. 
 * @param textMaxLines (multi_line_text) The maximum number of lines allowed on a multi-line text input. 
 * @param numberLimited (numbers_only_text) Flag to limit the value of a number option. 
 * @param numberLimitMode (numbers_only_text) The type of limit on values entered for a number option. 
 * @param numberLowestValue (numbers_only_text) The lowest allowed value for a number option if `number_limited` is true. 
 * @param numberHighestValue (numbers_only_text) The highest allowed value for a number option if `number_limited` is true. 
 * @param numberIntegersOnly (numbers_only_text) Flag to limit the input on a number option to whole numbers only. 
 * @param productListAdjustsInventory (product_list, product_list_with_images) Flag for automatically adjusting inventory on a product included in the list. 
 * @param productListAdjustsPricing (product_list, product_list_with_images) Flag to add the optional product's price to the main product's price. 
 * @param productListShippingCalc (product_list, product_list_with_images) How to factor the optional product's weight and package dimensions into the shipping quote. Values: `none` - don't adjust; `weight` - use shipping weight only; `package` - use weight and dimensions. 
 */
@JsonClass(generateAdapter = true)
data class ConfigFull (

    /* (date, text, multi_line_text, numbers_only_text) The default value. Shown on a date option as an ISO-8601–formatted string, or on a text option as a string.  */
    @Json(name = "default_value")
    val defaultValue: kotlin.String? = null,

    /* (checkbox) Flag for setting the checkbox to be checked by default.  */
    @Json(name = "checked_by_default")
    val checkedByDefault: kotlin.Boolean? = null,

    /* (checkbox) Label displayed for the checkbox option.  */
    @Json(name = "checkbox_label")
    val checkboxLabel: kotlin.String? = null,

    /* (date) Flag to limit the dates allowed to be entered on a date option.  */
    @Json(name = "date_limited")
    val dateLimited: kotlin.Boolean? = null,

    /* (date) The type of limit that is allowed to be entered on a date option.  */
    @Json(name = "date_limit_mode")
    val dateLimitMode: ConfigFull.DateLimitMode? = null,

    /* (date) The earliest date allowed to be entered on the date option, as an ISO-8601 formatted string.  */
    @Json(name = "date_earliest_value")
    val dateEarliestValue: java.time.LocalDate? = null,

    /* (date) The latest date allowed to be entered on the date option, as an ISO-8601 formatted string.  */
    @Json(name = "date_latest_value")
    val dateLatestValue: java.time.LocalDate? = null,

    /* (file) The kind of restriction on the file types that can be uploaded with a file upload option. Values: `specific` - restricts uploads to particular file types; `all` - allows all file types.  */
    @Json(name = "file_types_mode")
    val fileTypesMode: ConfigFull.FileTypesMode? = null,

    /* (file) The type of files allowed to be uploaded if the `file_type_option` is set to `specific`. Values:   `images` - Allows upload of image MIME types (`bmp`, `gif`, `jpg`, `jpeg`, `jpe`, `jif`, `jfif`, `jfi`, `png`, `wbmp`, `xbm`, `tiff`). `documents` - Allows upload of document MIME types (`txt`, `pdf`, `rtf`, `doc`, `docx`, `xls`, `xlsx`, `accdb`, `mdb`, `one`, `pps`, `ppsx`, `ppt`, `pptx`, `pub`, `odt`, `ods`, `odp`, `odg`, `odf`).   `other` - Allows file types defined in the `file_types_other` array.  */
    @Json(name = "file_types_supported")
    val fileTypesSupported: kotlin.collections.List<kotlin.String>? = null,

    /* (file) A list of other file types allowed with the file upload option.  */
    @Json(name = "file_types_other")
    val fileTypesOther: kotlin.collections.List<kotlin.String>? = null,

    /* (file) The maximum size for a file that can be used with the file upload option. This will still be limited by the server.  */
    @Json(name = "file_max_size")
    val fileMaxSize: kotlin.Int? = null,

    /* (text, multi_line_text) Flag to validate the length of a text or multi-line text input.  */
    @Json(name = "text_characters_limited")
    val textCharactersLimited: kotlin.Boolean? = null,

    /* (text, multi_line_text) The minimum length allowed for a text or multi-line text option.  */
    @Json(name = "text_min_length")
    val textMinLength: kotlin.Int? = null,

    /* (text, multi_line_text) The maximum length allowed for a text or multi line text option.  */
    @Json(name = "text_max_length")
    val textMaxLength: kotlin.Int? = null,

    /* (multi_line_text) Flag to validate the maximum number of lines allowed on a multi-line text input.  */
    @Json(name = "text_lines_limited")
    val textLinesLimited: kotlin.Boolean? = null,

    /* (multi_line_text) The maximum number of lines allowed on a multi-line text input.  */
    @Json(name = "text_max_lines")
    val textMaxLines: kotlin.Int? = null,

    /* (numbers_only_text) Flag to limit the value of a number option.  */
    @Json(name = "number_limited")
    val numberLimited: kotlin.Boolean? = null,

    /* (numbers_only_text) The type of limit on values entered for a number option.  */
    @Json(name = "number_limit_mode")
    val numberLimitMode: ConfigFull.NumberLimitMode? = null,

    /* (numbers_only_text) The lowest allowed value for a number option if `number_limited` is true.  */
    @Json(name = "number_lowest_value")
    val numberLowestValue: java.math.BigDecimal? = null,

    /* (numbers_only_text) The highest allowed value for a number option if `number_limited` is true.  */
    @Json(name = "number_highest_value")
    val numberHighestValue: java.math.BigDecimal? = null,

    /* (numbers_only_text) Flag to limit the input on a number option to whole numbers only.  */
    @Json(name = "number_integers_only")
    val numberIntegersOnly: kotlin.Boolean? = null,

    /* (product_list, product_list_with_images) Flag for automatically adjusting inventory on a product included in the list.  */
    @Json(name = "product_list_adjusts_inventory")
    val productListAdjustsInventory: kotlin.Boolean? = null,

    /* (product_list, product_list_with_images) Flag to add the optional product's price to the main product's price.  */
    @Json(name = "product_list_adjusts_pricing")
    val productListAdjustsPricing: kotlin.Boolean? = null,

    /* (product_list, product_list_with_images) How to factor the optional product's weight and package dimensions into the shipping quote. Values: `none` - don't adjust; `weight` - use shipping weight only; `package` - use weight and dimensions.  */
    @Json(name = "product_list_shipping_calc")
    val productListShippingCalc: ConfigFull.ProductListShippingCalc? = null

) {

    /**
     * (date) The type of limit that is allowed to be entered on a date option. 
     *
     * Values: earliest,range,latest
     */
    enum class DateLimitMode(val value: kotlin.String) {
        @Json(name = "earliest") earliest("earliest"),
        @Json(name = "range") range("range"),
        @Json(name = "latest") latest("latest");
    }
    /**
     * (file) The kind of restriction on the file types that can be uploaded with a file upload option. Values: `specific` - restricts uploads to particular file types; `all` - allows all file types. 
     *
     * Values: specific,all
     */
    enum class FileTypesMode(val value: kotlin.String) {
        @Json(name = "specific") specific("specific"),
        @Json(name = "all") all("all");
    }
    /**
     * (numbers_only_text) The type of limit on values entered for a number option. 
     *
     * Values: lowest,highest,range
     */
    enum class NumberLimitMode(val value: kotlin.String) {
        @Json(name = "lowest") lowest("lowest"),
        @Json(name = "highest") highest("highest"),
        @Json(name = "range") range("range");
    }
    /**
     * (product_list, product_list_with_images) How to factor the optional product's weight and package dimensions into the shipping quote. Values: `none` - don't adjust; `weight` - use shipping weight only; `package` - use weight and dimensions. 
     *
     * Values: none,weight,`package`
     */
    enum class ProductListShippingCalc(val value: kotlin.String) {
        @Json(name = "none") none("none"),
        @Json(name = "weight") weight("weight"),
        @Json(name = "package") `package`("package");
    }
}

