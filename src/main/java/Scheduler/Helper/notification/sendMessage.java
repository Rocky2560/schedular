package Scheduler.Helper.notification;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class sendMessage {
    /**
     * this methood  send the message to mattermost when scheduler fails during execution.
     * channel name is used for sending message to respective user.
     * username is the person that send exception message in mattermost.
     * @param msg consist of the exception message caused during failure of running scheduler.
     */
    public static void sendMessageToMattermost(String msg){
        try {
            String h_url="https://ekbana.letsperk.com/hooks/kqndtuee7ig4jnfz19f7byoqxr";
            String channel= "@sudip";
            String username = "oabot" ;
//            String h_url = config.getString("hook_url");
            JSONArray jar = new JSONArray();
            jar.put(new JSONObject().put("text", msg));
            JSONObject job = new JSONObject();
            job.put("attachments", jar);
            job.put("channel",channel);
            job.put("username",username);
//    HttpResponse<JsonNode> response =
            Unirest.post(h_url)
                    .header("Content-Type", "application/json")
                    .header("Charset", "UTF-8")
                    .body(job.toString())
                    .asJson();
        }catch (Exception e){

            e.printStackTrace();
//            log.error(e.getMessage(), e);
        }
    }
//
//    public static void main(String[] args) {
//        try {
//            String h_url="https://ekbana.letsperk.com/hooks/kqndtuee7ig4jnfz19f7byoqxr";
//            String channel= "@sudip";
//            String username = "oabot" ;
////            String h_url = config.getString("hook_url");
//            JSONArray jar = new JSONArray();
//            jar.put(new JSONObject().put("text", "its test"));
//            JSONObject job = new JSONObject();
//            job.put("attachments", jar);
//            job.put("channel",channel);
//            job.put("username",username);
////    HttpResponse<JsonNode> response =
//            Unirest.post(h_url)
//                    .header("Content-Type", "application/json")
//                    .header("Charset", "UTF-8")
//                    .body(job.toString())
//                    .asJson();
//        }catch (Exception e){
//
//            e.printStackTrace();
////            log.error(e.getMessage(), e);
//        }
//    }
    }

