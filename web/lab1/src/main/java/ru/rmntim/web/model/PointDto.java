package ru.rmntim.web.model;

import jakarta.validation.constraints.NotNull;
import ru.rmntim.web.validation.MaxDecimalPlaces;
import ru.rmntim.web.validation.Range;

public class PointDto {
    @NotNull(message = "x is invalid")
    @Range(min = -5, max = 3, message = "x has forbidden value")
    @MaxDecimalPlaces(value = 6, message = "x has too many decimal places")
    private String x;

    @NotNull(message = "y is invalid")
    @Range(min = -5, max = 3, message = "y has forbidden value")
    @MaxDecimalPlaces(value = 6, message = "y has too many decimal places")
    private String y;

    @NotNull(message = "r is invalid")
    @Range(min = 1, max = 5, message = "r has forbidden value")
    @MaxDecimalPlaces(value = 6, message = "r has too many decimal places")
    private String r;

    public PointDto() { }

    public PointDto(String x, String y, String r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public String getX() { return x; }
    public void setX(String x) { this.x = x; }
    public String getY() { return y; }
    public void setY(String y) { this.y = y; }
    public String getR() { return r; }
    public void setR(String r) { this.r = r; }
}
