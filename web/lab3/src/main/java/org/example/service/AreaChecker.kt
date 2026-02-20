package org.example.service

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
open class AreaChecker {

    open fun checkHit(x: Double, y: Double, r: Double): Boolean {
        val inRectangle = x >= -r && x <= 0 && y >= -r && y <= 0
        val inTriangle = x >= -r / 2 && x <= 0 && y >= 0 && y <= 2 * x + r
        val inQuarterCircle = x >= 0 && y >= 0 && (x * x + y * y <= (r * r) / 4)
        return inRectangle || inTriangle || inQuarterCircle
    }
}

