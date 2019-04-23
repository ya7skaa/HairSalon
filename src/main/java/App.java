import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";


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

        get("/clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/client_form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("clients/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
             Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
             model.put("stylist", stylist);
            model.put("stylists", Stylist.all());
            model.put("template", "templates/client_form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/clients", (request, response) -> {
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

//        get("stylists/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
//            model.put("stylist", stylist);
//            model.put("stylists", Stylist.all());
//            model.put("template", "templates/stylist.vtl");
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());

        post("/clients", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylist_id")));
            int stylist_id = stylist.getId();
            String name = request.queryParams("name");
            String gender = request.queryParams("gender");
            String contact = request.queryParams("contact");

            Client newClient = new Client(name, gender, contact,stylist.getId());
            newClient.save();
//            response.redirect("/stylists/"+ stylistId);
            model.put("template", "templates/stylist.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());
//        get("/clients", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("clients", Client.all());
//            model.put("template", "templates/clients.vtl");
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());
    }
}
