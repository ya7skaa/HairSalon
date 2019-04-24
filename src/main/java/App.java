import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        port(3456);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());



        get("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());




        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");
            Stylist newStylist = new Stylist(name, gender, contact);
            newStylist.save();
            model.put("stylists",Stylist.all());
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());




        get("/stylists/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/stylist_form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/stylists", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");
            Stylist newStylist = new Stylist(name, gender, contact);
            newStylist.save();
            model.put("template", "templates/stylists.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylist/:id/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
             Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
             model.put("stylist", stylist);
            model.put("stylists", Stylist.all());
            model.put("template", "templates/client_form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/clients/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("clients", Client.all());
            model.put("template","templates/clients.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("stylists/:id/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("stylists", Stylist.all());
            model.put("template", "templates/client_form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//

        post("/clients", (request, response) -> {

            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylist_id")));
            int stylistId = stylist.getId();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");
            Client newClient = new Client(name, gender, contact, stylist.getId());
            newClient.save();
            response.redirect("/stylists/"+ stylistId);
            return null;
        });



        //Updating stylist Details
        get("/stylists/:stylist_id/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            Stylist stylist = Stylist.find(client.getStylistId());
            model.put("client",client);
            model.put("template", "templates/edit_client_form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//        post("/stylists/:stylist_id/clients/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Client client = Client.find(Integer.parseInt(request.params("id")));
//            String name = request.queryParams("name");
//            String gender = request.queryParams("gender");
//            String contact = request.queryParams("contact");
//
//
//            Stylist stylist = Stylist.find(client.getStylistId());
//            client.update(name, gender, contact);
//            String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId());
//            response.redirect(url);
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());

       // updating the client
        post("/stylists/:stylist_id/clients/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params("id")));
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");
            Stylist stylist = Stylist.find(client.getStylistId());
            client.update(name,gender,contact);
            String url = String.format("/stylists/%d", stylist.getId());
            response.redirect(url);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

       // deleting the client
        post("/delete/client/:id", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            Client client = Client.find(Integer.parseInt(request.params(":id")));
            Stylist stylist = Stylist.find(client.getStylistId());
            client.delete();
            model.put("stylist", stylist);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/about_us", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/about_us.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
