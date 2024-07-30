import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {

    public static void main(String[] args) {
        try {
            // Prompt user for input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter base currency (e.g., USD): ");
            String baseCurrency = reader.readLine().toUpperCase();
            System.out.print("Enter target currency (e.g., EUR): ");
            String targetCurrency = reader.readLine().toUpperCase();
            System.out.print("Enter amount to convert: ");
            double amount = Double.parseDouble(reader.readLine());

            // Fetch exchange rates from API
            String apiKey = "your_api_key"; // Replace with your API key
            String apiUrl = "https://api.exchangeratesapi.io/latest?base=" + baseCurrency + "&symbols=" + targetCurrency;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HTTP error code: " + responseCode);
            }

            BufferedReader apiReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder apiResponse = new StringBuilder();
            String line;
            while ((line = apiReader.readLine()) != null) {
                apiResponse.append(line);
            }
            apiReader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(apiResponse.toString());
            double exchangeRate = jsonResponse.getJSONObject("rates").getDouble(targetCurrency);

            // Calculate converted amount
            double convertedAmount = amount * exchangeRate;

            // Display result
            System.out.println(amount + " " + baseCurrency + " is equivalent to " + convertedAmount + " " + targetCurrency);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
