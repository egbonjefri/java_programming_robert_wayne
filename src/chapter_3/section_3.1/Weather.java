/*
 * ind a website that publishes the current temperature in your area, and
write a screen-scraper program Weather so that typing java Weather followed by
your ZIP code will give you a weather forecast.
 */

import lib.StdOut;
import lib.In;



public class Weather {
    
    public static void main(String[] args){
        String postalCode = args[0];
        In page = new In("https://weather.gc.ca/city/pages/" + postalCode + "_metric_e.html");
        String attribute = "data-v-363ec2ac";
        String html = page.readAll();

        String startTagPattern = "<td " + attribute + ">";

         // Find the start of the tag
         int startTagIndex = html.indexOf(startTagPattern);
        
         if (startTagIndex == -1) {
             StdOut.println("Attribute not found");
         }
         
         // Adjust the index to get the position right after the start tag
         startTagIndex += startTagPattern.length();
         
         // Find the end of the tag
         int endTagIndex = html.indexOf("</td>", startTagIndex);
         
         if (endTagIndex == -1) {
            StdOut.println("End tag not found");
         }
         
         // Extract and return the text within the tags
         StdOut.println(html.substring(startTagIndex, endTagIndex).trim());
    }
}
