package com.alexitc.coinalerts.tasks.models

import com.alexitc.coinalerts.models.Alert

case class FixedPriceAlertEvent(
    alert: Alert,
    currentPrice: BigDecimal)
