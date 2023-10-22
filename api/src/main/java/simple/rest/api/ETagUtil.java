package simple.rest.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ETagUtil {

        public static String calculateETag(List<?> list) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                StringBuilder sb = new StringBuilder();
                for (Object item : list) {
                    sb.append(item.toString());
                }
                byte[] hash = md.digest(sb.toString().getBytes());
                StringBuilder hexString = new StringBuilder();
                for (byte b : hash) {
                    hexString.append(String.format("%02x", b));
                }

                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
}
