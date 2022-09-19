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
 * The model for a PUT to update a product.
 *
 * @param name The product name. 
 * @param type The product type. One of: `physical` - a physical stock unit, `digital` - a digital download. 
 * @param weight Weight of the product, which can be used when calculating shipping costs. This is based on the unit set on the store 
 * @param price The price of the product. The price should include or exclude tax, based on the store settings. 
 * @param id The unique numerical ID of the product; increments sequentially. 
 * @param sku User defined product code/stock keeping unit (SKU). 
 * @param description The product description, which can include HTML formatting. 
 * @param width Width of the product, which can be used when calculating shipping costs. 
 * @param depth Depth of the product, which can be used when calculating shipping costs. 
 * @param height Height of the product, which can be used when calculating shipping costs. 
 * @param costPrice The cost price of the product. Stored for reference only; it is not used or displayed anywhere on the store. 
 * @param retailPrice The retail cost of the product. If entered, the retail cost price will be shown on the product page. 
 * @param salePrice If entered, the sale price will be used instead of value in the price field when calculating the product's cost. 
 * @param mapPrice Minimum Advertised Price
 * @param taxClassId The ID of the tax class applied to the product. (NOTE: Value ignored if automatic tax is enabled.) 
 * @param productTaxCode Accepts AvaTax System Tax Codes, which identify products and services that fall into special sales-tax categories. By using these codes, merchants who subscribe to BigCommerce's Avalara Premium integration can calculate sales taxes more accurately. Stores without Avalara Premium will ignore the code when calculating sales tax. Do not pass more than one code. The codes are case-sensitive. For details, please see Avalara's documentation. 
 * @param categories An array of IDs for the categories to which this product belongs. When updating a product, if an array of categories is supplied, all product categories will be overwritten. Does not accept more than 1,000 ID values. 
 * @param brandId A product can be added to an existing brand during a product /PUT or /POST. 
 * @param inventoryLevel Current inventory level of the product. Simple inventory tracking must be enabled (See the `inventory_tracking` field) for this to take any effect. 
 * @param inventoryWarningLevel Inventory warning level for the product. When the product's inventory level drops below the warning level, the store owner will be informed. Simple inventory tracking must be enabled (see the `inventory_tracking` field) for this to take any effect. 
 * @param inventoryTracking The type of inventory tracking for the product. Values are: `none` - inventory levels will not be tracked; `product` - inventory levels will be tracked using the `inventory_level` and `inventory_warning_level` fields; `variant` - inventory levels will be tracked based on variants, which maintain their own warning levels and inventory levels. 
 * @param fixedCostShippingPrice A fixed shipping cost for the product. If defined, this value will be used during checkout instead of normal shipping-cost calculation. 
 * @param isFreeShipping Flag used to indicate whether the product has free shipping. If `true`, the shipping cost for the product will be zero. 
 * @param isVisible Flag to determine whether the product should be displayed to customers browsing the store. If `true`, the product will be displayed. If `false`, the product will be hidden from view. 
 * @param isFeatured Flag to determine whether the product should be included in the `featured products` panel when viewing the store. 
 * @param relatedProducts An array of IDs for the related products. 
 * @param warranty Warranty information displayed on the product page. Can include HTML formatting. 
 * @param binPickingNumber The BIN picking number for the product. 
 * @param layoutFile The layout template file used to render this product category. This field is writable only for stores with a Blueprint theme applied. 
 * @param upc The product UPC code, which is used in feeds for shopping comparison sites and external channel integrations. 
 * @param searchKeywords A comma-separated list of keywords that can be used to locate the product when searching the store. 
 * @param availability Availability of the product. (Corresponds to the product's [Purchasability](https://support.bigcommerce.com/s/article/Adding-Products-v3?language=en_US#sections) section in the control panel.) Supported values: `available` - the product is available for purchase; `disabled` - the product is listed on the storefront, but cannot be purchased; `preorder` - the product is listed for pre-orders. 
 * @param availabilityDescription Availability text displayed on the checkout page, under the product title. Tells the customer how long it will normally take to ship this product, such as: 'Usually ships in 24 hours.' 
 * @param giftWrappingOptionsType Type of gift-wrapping options. Values: `any` - allow any gift-wrapping options in the store; `none` - disallow gift-wrapping on the product; `list` – provide a list of IDs in the `gift_wrapping_options_list` field. 
 * @param giftWrappingOptionsList A list of gift-wrapping option IDs. 
 * @param sortOrder Priority to give this product when included in product lists on category pages and in search results. Lower integers will place the product closer to the top of the results. 
 * @param condition The product condition. Will be shown on the product page if the `is_condition_shown` field's value is `true`. Possible values: `New`, `Used`, `Refurbished`. 
 * @param isConditionShown Flag used to determine whether the product condition is shown to the customer on the product page. 
 * @param orderQuantityMinimum The minimum quantity an order must contain, to be eligible to purchase this product. 
 * @param orderQuantityMaximum The maximum quantity an order can contain when purchasing the product. 
 * @param pageTitle Custom title for the product page. If not defined, the product name will be used as the meta title. 
 * @param metaKeywords Custom meta keywords for the product page. If not defined, the store's default keywords will be used. 
 * @param metaDescription Custom meta description for the product page. If not defined, the store's default meta description will be used. 
 * @param viewCount The number of times the product has been viewed. 
 * @param preorderReleaseDate Pre-order release date. See the `availability` field for details on setting a product's availability to accept pre-orders. 
 * @param preorderMessage Custom expected-date message to display on the product page. If undefined, the message defaults to the storewide setting. Can contain the `%%DATE%%` placeholder, which will be substituted for the release date. 
 * @param isPreorderOnly If set to true then on the preorder release date the preorder status will automatically be removed. If set to false, then on the release date the preorder status **will not** be removed. It will need to be changed manually either in the control panel or using the API. Using the API set `availability` to `available`. 
 * @param isPriceHidden False by default, indicating that this product's price should be shown on the product page. If set to `true`, the price is hidden. (NOTE: To successfully set `is_price_hidden` to `true`, the `availability` value must be `disabled`.) 
 * @param priceHiddenLabel By default, an empty string. If `is_price_hidden` is `true`, the value of `price_hidden_label` is displayed instead of the price. (NOTE: To successfully set a non-empty string value with `is_price_hidden` set to `true`, the `availability` value must be `disabled`.) 
 * @param customUrl 
 * @param openGraphType Type of product, defaults to `product`. 
 * @param openGraphTitle Title of the product, if not specified the product name will be used instead. 
 * @param openGraphDescription Description to use for the product, if not specified then the meta_description will be used instead. 
 * @param openGraphUseMetaDescription Flag to determine if product description or open graph description is used. 
 * @param openGraphUseProductName Flag to determine if product name or open graph name is used. 
 * @param openGraphUseImage Flag to determine if product image or open graph image is used. 
 * @param brandNameOrBrandId The brand can be created during a product PUT or POST request. If the brand already exists then the product will be added. If not the brand will be created and the product added. If using `brand_name` it performs a fuzzy match and adds the brand. eg. \"Common Good\" and \"Common good\" are the same. Brand name does not return as part of a product response. Only the `brand_id`.
 * @param gtin Global Trade Item Number
 * @param mpn Manufacturer Part Number
 * @param reviewsRatingSum The total rating for the product. 
 * @param reviewsCount The number of times the product has been rated. 
 * @param totalSold The total quantity of this product sold. 
 * @param customFields 
 * @param bulkPricingRules 
 * @param images 
 * @param videos 
 * @param variants 
 */
@JsonClass(generateAdapter = true)
data class ProductPut (

    /* The product name.  */
    @Json(name = "name")
    val name: kotlin.String,

    /* The product type. One of: `physical` - a physical stock unit, `digital` - a digital download.  */
    @Json(name = "type")
    val type: ProductPut.Type,

    /* Weight of the product, which can be used when calculating shipping costs. This is based on the unit set on the store  */
    @Json(name = "weight")
    val weight: kotlin.Float,

    /* The price of the product. The price should include or exclude tax, based on the store settings.  */
    @Json(name = "price")
    val price: kotlin.Float,

    /* The unique numerical ID of the product; increments sequentially.  */
    @Json(name = "id")
    val id: kotlin.Int? = null,

    /* User defined product code/stock keeping unit (SKU).  */
    @Json(name = "sku")
    val sku: kotlin.String? = null,

    /* The product description, which can include HTML formatting.  */
    @Json(name = "description")
    val description: kotlin.String? = null,

    /* Width of the product, which can be used when calculating shipping costs.  */
    @Json(name = "width")
    val width: kotlin.Float? = null,

    /* Depth of the product, which can be used when calculating shipping costs.  */
    @Json(name = "depth")
    val depth: kotlin.Float? = null,

    /* Height of the product, which can be used when calculating shipping costs.  */
    @Json(name = "height")
    val height: kotlin.Float? = null,

    /* The cost price of the product. Stored for reference only; it is not used or displayed anywhere on the store.  */
    @Json(name = "cost_price")
    val costPrice: kotlin.Float? = null,

    /* The retail cost of the product. If entered, the retail cost price will be shown on the product page.  */
    @Json(name = "retail_price")
    val retailPrice: kotlin.Float? = null,

    /* If entered, the sale price will be used instead of value in the price field when calculating the product's cost.  */
    @Json(name = "sale_price")
    val salePrice: kotlin.Float? = null,

    /* Minimum Advertised Price */
    @Json(name = "map_price")
    val mapPrice: java.math.BigDecimal? = null,

    /* The ID of the tax class applied to the product. (NOTE: Value ignored if automatic tax is enabled.)  */
    @Json(name = "tax_class_id")
    val taxClassId: kotlin.Int? = null,

    /* Accepts AvaTax System Tax Codes, which identify products and services that fall into special sales-tax categories. By using these codes, merchants who subscribe to BigCommerce's Avalara Premium integration can calculate sales taxes more accurately. Stores without Avalara Premium will ignore the code when calculating sales tax. Do not pass more than one code. The codes are case-sensitive. For details, please see Avalara's documentation.  */
    @Json(name = "product_tax_code")
    val productTaxCode: kotlin.String? = null,

    /* An array of IDs for the categories to which this product belongs. When updating a product, if an array of categories is supplied, all product categories will be overwritten. Does not accept more than 1,000 ID values.  */
    @Json(name = "categories")
    val categories: kotlin.collections.List<kotlin.Int>? = null,

    /* A product can be added to an existing brand during a product /PUT or /POST.  */
    @Json(name = "brand_id")
    val brandId: kotlin.Int? = null,

    /* Current inventory level of the product. Simple inventory tracking must be enabled (See the `inventory_tracking` field) for this to take any effect.  */
    @Json(name = "inventory_level")
    val inventoryLevel: kotlin.Int? = null,

    /* Inventory warning level for the product. When the product's inventory level drops below the warning level, the store owner will be informed. Simple inventory tracking must be enabled (see the `inventory_tracking` field) for this to take any effect.  */
    @Json(name = "inventory_warning_level")
    val inventoryWarningLevel: kotlin.Int? = null,

    /* The type of inventory tracking for the product. Values are: `none` - inventory levels will not be tracked; `product` - inventory levels will be tracked using the `inventory_level` and `inventory_warning_level` fields; `variant` - inventory levels will be tracked based on variants, which maintain their own warning levels and inventory levels.  */
    @Json(name = "inventory_tracking")
    val inventoryTracking: ProductPut.InventoryTracking? = null,

    /* A fixed shipping cost for the product. If defined, this value will be used during checkout instead of normal shipping-cost calculation.  */
    @Json(name = "fixed_cost_shipping_price")
    val fixedCostShippingPrice: kotlin.Float? = null,

    /* Flag used to indicate whether the product has free shipping. If `true`, the shipping cost for the product will be zero.  */
    @Json(name = "is_free_shipping")
    val isFreeShipping: kotlin.Boolean? = null,

    /* Flag to determine whether the product should be displayed to customers browsing the store. If `true`, the product will be displayed. If `false`, the product will be hidden from view.  */
    @Json(name = "is_visible")
    val isVisible: kotlin.Boolean? = null,

    /* Flag to determine whether the product should be included in the `featured products` panel when viewing the store.  */
    @Json(name = "is_featured")
    val isFeatured: kotlin.Boolean? = null,

    /* An array of IDs for the related products.  */
    @Json(name = "related_products")
    val relatedProducts: kotlin.collections.List<kotlin.Int>? = null,

    /* Warranty information displayed on the product page. Can include HTML formatting.  */
    @Json(name = "warranty")
    val warranty: kotlin.String? = null,

    /* The BIN picking number for the product.  */
    @Json(name = "bin_picking_number")
    val binPickingNumber: kotlin.String? = null,

    /* The layout template file used to render this product category. This field is writable only for stores with a Blueprint theme applied.  */
    @Json(name = "layout_file")
    val layoutFile: kotlin.String? = null,

    /* The product UPC code, which is used in feeds for shopping comparison sites and external channel integrations.  */
    @Json(name = "upc")
    val upc: kotlin.String? = null,

    /* A comma-separated list of keywords that can be used to locate the product when searching the store.  */
    @Json(name = "search_keywords")
    val searchKeywords: kotlin.String? = null,

    /* Availability of the product. (Corresponds to the product's [Purchasability](https://support.bigcommerce.com/s/article/Adding-Products-v3?language=en_US#sections) section in the control panel.) Supported values: `available` - the product is available for purchase; `disabled` - the product is listed on the storefront, but cannot be purchased; `preorder` - the product is listed for pre-orders.  */
    @Json(name = "availability")
    val availability: ProductPut.Availability? = null,

    /* Availability text displayed on the checkout page, under the product title. Tells the customer how long it will normally take to ship this product, such as: 'Usually ships in 24 hours.'  */
    @Json(name = "availability_description")
    val availabilityDescription: kotlin.String? = null,

    /* Type of gift-wrapping options. Values: `any` - allow any gift-wrapping options in the store; `none` - disallow gift-wrapping on the product; `list` – provide a list of IDs in the `gift_wrapping_options_list` field.  */
    @Json(name = "gift_wrapping_options_type")
    val giftWrappingOptionsType: ProductPut.GiftWrappingOptionsType? = null,

    /* A list of gift-wrapping option IDs.  */
    @Json(name = "gift_wrapping_options_list")
    val giftWrappingOptionsList: kotlin.collections.List<kotlin.Int>? = null,

    /* Priority to give this product when included in product lists on category pages and in search results. Lower integers will place the product closer to the top of the results.  */
    @Json(name = "sort_order")
    val sortOrder: kotlin.Int? = null,

    /* The product condition. Will be shown on the product page if the `is_condition_shown` field's value is `true`. Possible values: `New`, `Used`, `Refurbished`.  */
    @Json(name = "condition")
    val condition: ProductPut.Condition? = null,

    /* Flag used to determine whether the product condition is shown to the customer on the product page.  */
    @Json(name = "is_condition_shown")
    val isConditionShown: kotlin.Boolean? = null,

    /* The minimum quantity an order must contain, to be eligible to purchase this product.  */
    @Json(name = "order_quantity_minimum")
    val orderQuantityMinimum: kotlin.Int? = null,

    /* The maximum quantity an order can contain when purchasing the product.  */
    @Json(name = "order_quantity_maximum")
    val orderQuantityMaximum: kotlin.Int? = null,

    /* Custom title for the product page. If not defined, the product name will be used as the meta title.  */
    @Json(name = "page_title")
    val pageTitle: kotlin.String? = null,

    /* Custom meta keywords for the product page. If not defined, the store's default keywords will be used.  */
    @Json(name = "meta_keywords")
    val metaKeywords: kotlin.collections.List<kotlin.String>? = null,

    /* Custom meta description for the product page. If not defined, the store's default meta description will be used.  */
    @Json(name = "meta_description")
    val metaDescription: kotlin.String? = null,

    /* The number of times the product has been viewed.  */
    @Json(name = "view_count")
    val viewCount: kotlin.Int? = null,

    /* Pre-order release date. See the `availability` field for details on setting a product's availability to accept pre-orders.  */
    @Json(name = "preorder_release_date")
    val preorderReleaseDate: java.time.OffsetDateTime? = null,

    /* Custom expected-date message to display on the product page. If undefined, the message defaults to the storewide setting. Can contain the `%%DATE%%` placeholder, which will be substituted for the release date.  */
    @Json(name = "preorder_message")
    val preorderMessage: kotlin.String? = null,

    /* If set to true then on the preorder release date the preorder status will automatically be removed. If set to false, then on the release date the preorder status **will not** be removed. It will need to be changed manually either in the control panel or using the API. Using the API set `availability` to `available`.  */
    @Json(name = "is_preorder_only")
    val isPreorderOnly: kotlin.Boolean? = null,

    /* False by default, indicating that this product's price should be shown on the product page. If set to `true`, the price is hidden. (NOTE: To successfully set `is_price_hidden` to `true`, the `availability` value must be `disabled`.)  */
    @Json(name = "is_price_hidden")
    val isPriceHidden: kotlin.Boolean? = null,

    /* By default, an empty string. If `is_price_hidden` is `true`, the value of `price_hidden_label` is displayed instead of the price. (NOTE: To successfully set a non-empty string value with `is_price_hidden` set to `true`, the `availability` value must be `disabled`.)  */
    @Json(name = "price_hidden_label")
    val priceHiddenLabel: kotlin.String? = null,

    @Json(name = "custom_url")
    val customUrl: CustomUrlFull? = null,

    /* Type of product, defaults to `product`.  */
    @Json(name = "open_graph_type")
    val openGraphType: ProductPut.OpenGraphType? = null,

    /* Title of the product, if not specified the product name will be used instead.  */
    @Json(name = "open_graph_title")
    val openGraphTitle: kotlin.String? = null,

    /* Description to use for the product, if not specified then the meta_description will be used instead.  */
    @Json(name = "open_graph_description")
    val openGraphDescription: kotlin.String? = null,

    /* Flag to determine if product description or open graph description is used.  */
    @Json(name = "open_graph_use_meta_description")
    val openGraphUseMetaDescription: kotlin.Boolean? = null,

    /* Flag to determine if product name or open graph name is used.  */
    @Json(name = "open_graph_use_product_name")
    val openGraphUseProductName: kotlin.Boolean? = null,

    /* Flag to determine if product image or open graph image is used.  */
    @Json(name = "open_graph_use_image")
    val openGraphUseImage: kotlin.Boolean? = null,

    /* The brand can be created during a product PUT or POST request. If the brand already exists then the product will be added. If not the brand will be created and the product added. If using `brand_name` it performs a fuzzy match and adds the brand. eg. \"Common Good\" and \"Common good\" are the same. Brand name does not return as part of a product response. Only the `brand_id`. */
    @Json(name = "brand_name or brand_id")
    val brandNameOrBrandId: kotlin.String? = null,

    /* Global Trade Item Number */
    @Json(name = "gtin")
    val gtin: kotlin.String? = null,

    /* Manufacturer Part Number */
    @Json(name = "mpn")
    val mpn: kotlin.String? = null,

    /* The total rating for the product.  */
    @Json(name = "reviews_rating_sum")
    val reviewsRatingSum: kotlin.Int? = null,

    /* The number of times the product has been rated.  */
    @Json(name = "reviews_count")
    val reviewsCount: kotlin.Int? = null,

    /* The total quantity of this product sold.  */
    @Json(name = "total_sold")
    val totalSold: kotlin.Int? = null,

    @Json(name = "custom_fields")
    val customFields: kotlin.collections.List<ProductCustomFieldPut>? = null,

    @Json(name = "bulk_pricing_rules")
    val bulkPricingRules: kotlin.collections.List<BulkPricingRuleFull>? = null,

    @Json(name = "images")
    val images: kotlin.collections.List<ProductImageFull>? = null,

    @Json(name = "videos")
    val videos: kotlin.collections.List<ProductVideoFull>? = null,

    @Json(name = "variants")
    val variants: kotlin.collections.List<ProductVariantPutProduct>? = null

) {

    /**
     * The product type. One of: `physical` - a physical stock unit, `digital` - a digital download. 
     *
     * Values: physical,digital
     */
    enum class Type(val value: kotlin.String) {
        @Json(name = "physical") physical("physical"),
        @Json(name = "digital") digital("digital");
    }
    /**
     * The type of inventory tracking for the product. Values are: `none` - inventory levels will not be tracked; `product` - inventory levels will be tracked using the `inventory_level` and `inventory_warning_level` fields; `variant` - inventory levels will be tracked based on variants, which maintain their own warning levels and inventory levels. 
     *
     * Values: none,product,variant
     */
    enum class InventoryTracking(val value: kotlin.String) {
        @Json(name = "none") none("none"),
        @Json(name = "product") product("product"),
        @Json(name = "variant") variant("variant");
    }
    /**
     * Availability of the product. (Corresponds to the product's [Purchasability](https://support.bigcommerce.com/s/article/Adding-Products-v3?language=en_US#sections) section in the control panel.) Supported values: `available` - the product is available for purchase; `disabled` - the product is listed on the storefront, but cannot be purchased; `preorder` - the product is listed for pre-orders. 
     *
     * Values: available,disabled,preorder
     */
    enum class Availability(val value: kotlin.String) {
        @Json(name = "available") available("available"),
        @Json(name = "disabled") disabled("disabled"),
        @Json(name = "preorder") preorder("preorder");
    }
    /**
     * Type of gift-wrapping options. Values: `any` - allow any gift-wrapping options in the store; `none` - disallow gift-wrapping on the product; `list` – provide a list of IDs in the `gift_wrapping_options_list` field. 
     *
     * Values: any,none,list
     */
    enum class GiftWrappingOptionsType(val value: kotlin.String) {
        @Json(name = "any") any("any"),
        @Json(name = "none") none("none"),
        @Json(name = "list") list("list");
    }
    /**
     * The product condition. Will be shown on the product page if the `is_condition_shown` field's value is `true`. Possible values: `New`, `Used`, `Refurbished`. 
     *
     * Values: new,used,refurbished
     */
    enum class Condition(val value: kotlin.String) {
        @Json(name = "New") new("New"),
        @Json(name = "Used") used("Used"),
        @Json(name = "Refurbished") refurbished("Refurbished");
    }
    /**
     * Type of product, defaults to `product`. 
     *
     * Values: product,album,book,drink,food,game,movie,song,tvShow
     */
    enum class OpenGraphType(val value: kotlin.String) {
        @Json(name = "product") product("product"),
        @Json(name = "album") album("album"),
        @Json(name = "book") book("book"),
        @Json(name = "drink") drink("drink"),
        @Json(name = "food") food("food"),
        @Json(name = "game") game("game"),
        @Json(name = "movie") movie("movie"),
        @Json(name = "song") song("song"),
        @Json(name = "tv_show") tvShow("tv_show");
    }
}

