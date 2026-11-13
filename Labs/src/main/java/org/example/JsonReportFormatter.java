package org.example;

import java.util.List;

public class JsonReportFormatter implements ReportFormatter {

    @Override
    public String formatCars(List<Car> cars) {
        StringBuilder sb = new StringBuilder("{\"cars\":[");
        for (int i = 0; i < cars.size(); i++) {
            Car c = cars.get(i);
            if (i > 0) sb.append(",");
            sb.append("{\"plate\":\"").append(c.getLicensePlate())
                    .append("\",\"type\":\"").append(c.getVehicleType())
                    .append("\",\"class\":\"").append(c.getCarClass())
                    .append("\",\"status\":\"").append(c.getStatus())
                    .append("\"}");
        }
        return sb.append("]}").toString();
    }

    @Override
    public String formatDrivers(List<Driver> drivers) {
        StringBuilder sb = new StringBuilder("{\"drivers\":[");
        for (int i = 0; i < drivers.size(); i++) {
            Driver d = drivers.get(i);
            if (i > 0) sb.append(",");
            sb.append("{\"id\":").append(d.getId())
                    .append(",\"name\":\"").append(escape(d.getFullName()))
                    .append("\",\"role\":\"").append(d.getRole())
                    .append("\",\"rating\":").append(String.format("%.2f", d.getRating()))
                    .append("}");
        }
        return sb.append("]}").toString();
    }

    @Override
    public String formatRides(List<Ride> rides) {
        StringBuilder sb = new StringBuilder("{\"rides\":[");
        for (int i = 0; i < rides.size(); i++) {
            Ride r = rides.get(i);
            if (i > 0) sb.append(",");

            sb.append("{\"id\":").append(r.getRideId())
                    .append(",\"client\":\"").append(escape(r.getClient()))
                    .append("\",\"route\":{")
                    .append("\"origin\":\"").append(escape(r.getRoute().getOrigin()))
                    .append("\",\"destination\":\"").append(escape(r.getRoute().getDestination()))
                    .append("\",\"distanceKm\":").append(r.getRoute().getDistanceKm())
                    .append("}")
                    .append(",\"tariff\":{")
                    .append("\"name\":\"").append(escape(r.getTariff().getName()))
                    .append("\",\"pricePerKm\":").append(r.getTariff().getPricePerKm())
                    .append("}")
                    .append(",\"payment\":\"").append(escape(r.getPayment().toString()))
                    .append("\",\"status\":\"").append(r.getStatus())
                    .append("\",\"cost\":").append(String.format("%.2f", r.getCost()));

            // Оценка — опциональна (может быть null)
            if (r.getRating() != null) {
                Rating rating = r.getRating();
                sb.append(",\"rating\":{")
                        .append("\"value\":").append(rating.getValue())
                        .append(",\"comment\":\"").append(escape(rating.getComment()))
                        .append("\",\"date\":\"").append(rating.getDate())
                        .append("\"}");
            }

            sb.append("}");
        }
        return sb.append("]}").toString();
    }

    /** Экранирует кавычки и обратные слэши для корректного JSON. */
    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}