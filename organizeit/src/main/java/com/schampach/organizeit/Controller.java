package com.schampach.organizeit;

import com.schampach.organizeit.db.entity.Drawer;
import com.schampach.organizeit.db.entity.Item;
import com.schampach.organizeit.db.entity.Shelf;
import com.schampach.organizeit.db.repository.DrawerRepository;
import com.schampach.organizeit.db.repository.ShelfRepository;
import com.schampach.organizeit.db.service.DrawerService;
import com.schampach.organizeit.db.service.ItemService;
import com.schampach.organizeit.db.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//@org.springframework.stereotype.Controller
@org.springframework.stereotype.Controller
@SessionAttributes("endpointRegistry")
public class Controller {
    @Autowired
    private DrawerService drawerService;

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String index(Model model) {
        List<Shelf> shelves = shelfService.getAllShelf();
        List<Drawer> drawers = drawerService.getAllDrawer();
        model.addAttribute("shelves", shelves);
        model.addAttribute("drawers", drawers);
        return "index";
    }

    @GetMapping("/Drawers/{drawer}")
    public String readEndpoints(@PathVariable String drawer, Model model){
        List<Item> items = drawerService.getItemsByDrawerName(drawer);
        model.addAttribute("items", items);
        return "drawer";
    }

    @GetMapping("/hello")
    public String getGreetings(Model model){
        model.addAttribute("attribute1", "Hello");
        model.addAttribute("attribute2", "World");
        return "hello";
    }

    /*@ModelAttribute("endpointRegistry")
    public EndpointRegistry getEndpoints() {
        return new EndpointRegistry();
    }

    //Funktion wird aufgerufen, wenn "/getEndpoints/{file}" mit Get geöffnet wird.
    //Gibt endpoints.html zurück, liest die Json Dateien ein und übergibt diese dem Model
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private GeneratedFileService generatedFileService;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private PromptService promptService;
    @GetMapping("/getEndpoints/{file}")
    public String readEndpoints(@PathVariable String file, Model model, @ModelAttribute("endpointRegistry") EndpointRegistry endpointRegistry){
        JsonReader jsonReader = new JsonReader();
        try{
            Json data = jsonService.getJsonByName(file);
            endpointRegistry.setBaseUrl("https://bmdweb.bmd.at"+ jsonReader.getBaseUrl(data.getText()));
            endpointRegistry.setEndpointList(jsonReader.getEndpoints(data.getText()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        model.addAttribute("jsonNameList", jsonService.getJsonNameList());
        model.addAttribute("file", file);
        return "endpoints";
    }

    //Funktion wird aufgerufen, wenn "/getEndpoints/{file}/fillParameter" mit Post geöffnet wird.
    //Gibt fillParameter.html zurück und löscht unter alle Endpoints außer die, die im Body vorhanden sind
    @PostMapping("/getEndpoints/{file}/fillParameter")
    public String FillParameter(Model model, @PathVariable String file, @RequestBody String body, @ModelAttribute("endpointRegistry") EndpointRegistry endpointRegistry){
        String[] splitBody = body.split("&");
        List<Integer> endpointIds = new ArrayList<>();
        for (int i = 0; i<splitBody.length; i++) {

            splitBody[i] = decodeStringURL(splitBody[i].substring(0, splitBody[i].indexOf('=')));
            endpointIds.add(Integer.parseInt(splitBody[i]));
        }

        endpointRegistry.getEndpointList().removeIf(endpoint -> !endpointIds.contains(endpoint.getId()));
        model.addAttribute("file", file);
        model.addAttribute("promptLanguageList", promptService.getPromptLanguageList());
        return "fillParameter";
    }

    //Funktion wird aufgerufen, wenn "/getEndpoints/{file}/fillParameter/generate" mit Post geöffnet wird.
    //Gibt generate.html zurück und befüllt das Model mit nötigen Daten (unter anderem den generierten Code)
    @PostMapping("/getEndpoints/{file}/fillParameter/generate")
    public String generate(@PathVariable String file, Model model, @RequestBody String body, @ModelAttribute("endpointRegistry") EndpointRegistry endpointRegistry){
        String[] splitBody = body.split("&");
        List<String> tokenAuthValues = new ArrayList<>();
        List<String> parameterNames = new ArrayList<>();
        List<String> parameterValues = new ArrayList<>();
        String language = "JAVA";

        for (String string : splitBody) {
            String name = decodeStringURL(string.substring(0, string.indexOf('=')));
            String value = decodeStringURL(string.substring(string.indexOf('=') + 1));
            if (name.contains("::")) {
                parameterNames.add(name);
                parameterValues.add(value);
            }
            else if (name.equals("lang") && !value.isEmpty()){
                language = value.toUpperCase();
            }  else {
                tokenAuthValues.add(value);
            }
        }

        for (int i = 0; i<parameterNames.size(); i++) {
            String[] s = parameterNames.get(i).split("::");

            endpointRegistry.getEndpointList().get(endpointRegistry.getEndpointIndexById(Integer.parseInt(s[0])))
                    .getParameters().get(Integer.parseInt(s[1]))
                    .setData(parameterValues.get(i));
        }

        JavaCodegenerator javaCodegenerator = new JavaCodegenerator(endpointRegistry.getEndpointList(), endpointRegistry.getBaseUrl(), tokenAuthValues.get(0), tokenAuthValues.get(1), tokenAuthValues.get(2), language);
        String code = javaCodegenerator.generateCode();

        if (language.equalsIgnoreCase("PYTHON")) {
            PythonCodegenerator pythonCodegen = new PythonCodegenerator(endpointRegistry.getEndpointList(), endpointRegistry.getBaseUrl(), tokenAuthValues.get(0), tokenAuthValues.get(1), tokenAuthValues.get(2));
            code = pythonCodegen.generateCode();
        }

        if(!language.equalsIgnoreCase("JAVA") && !language.equalsIgnoreCase("PYTHON")){
            code = javaCodegenerator.translateCode(language, code, promptService);
        }

        generatedFileService.saveGeneratedFile(code);
        model.addAttribute("code", code);
        model.addAttribute("file", file);
        return "generate";
    }


    //Funktion wird aufgerufen, wenn "/getEndpoints/{file}/fillParameterJson" mit Post geöffnet wird.
    //Gibt fillParameterJson.html zurück und löscht unter alle Endpoints außer die, die im Body vorhanden sind
    @PostMapping("/getEndpoints/{file}/fillParameterJson")
    public String FillParameterJson(Model model, @PathVariable String file, @RequestBody String body, @ModelAttribute("endpointRegistry") EndpointRegistry endpointRegistry){
        String[] splitBody = body.split("&");
        List<Integer> endpointIds = new ArrayList<>();
        boolean hasBodyParameter = false;
        for (int i = 0; i<splitBody.length; i++) {
            splitBody[i] = decodeStringURL(splitBody[i].substring(0, splitBody[i].indexOf('=')));
            endpointIds.add(Integer.parseInt(splitBody[i]));

            for(Parameter parameter: endpointRegistry.getEndpointList().get(endpointRegistry.getEndpointIndexById(Integer.parseInt(splitBody[i]))).getParameters()){
                if(parameter.getLocation().equalsIgnoreCase("body")){
                    hasBodyParameter = true;
                }
            }
        }

        model.addAttribute("file", file);
        if(hasBodyParameter){
            endpointRegistry.getEndpointList().removeIf(endpoint -> !endpointIds.contains(endpoint.getId()));
            return "fillParameterJson";
        }else{
            model.addAttribute("jsonNameList", jsonService.getJsonNameList());
            model.addAttribute("noBody", true);
            return "endpoints";
        }
    }


    //Funktion wird aufgerufen, wenn "/getEndpoints/{file}/fillParameterJson/generateJson" mit Post geöffnet wird.
    //Gibt generateJson.html zurück und befüllt das Model mit nötigen Daten (unter anderem die generierte Json)
    @PostMapping("/getEndpoints/{file}/fillParameterJson/generateJson")
    public String generateJson(@PathVariable String file, Model model, @RequestBody String body, @ModelAttribute("endpointRegistry") EndpointRegistry endpointRegistry){
        String[] splitBody = body.split("&");
        List<String> parameterNames = new ArrayList<>();
        List<String> parameterValues = new ArrayList<>();
        for (String string : splitBody) {
            String name = decodeStringURL(string.substring(0, string.indexOf('=')));
            String value = decodeStringURL(string.substring(string.indexOf('=') + 1));
            if (name.contains("::")) {
                parameterNames.add(name);
                parameterValues.add(value);
            }
        }

        for (int i = 0; i<parameterNames.size(); i++) {
            String[] s = parameterNames.get(i).split("::");
            endpointRegistry.getEndpointList().get(endpointRegistry.getEndpointIndexById(Integer.parseInt(s[0])))
                    .getParameters().get(Integer.parseInt(s[1]))
                    .setData(parameterValues.get(i));
        }

        JsonGenerator jsonGenerator = new JsonGenerator(endpointRegistry.getEndpointList().get(0));
        String jsonFile = jsonGenerator.generateJson();
        generatedFileService.saveGeneratedFile(jsonFile);
        model.addAttribute("jsonFile", jsonFile);
        model.addAttribute("file", file);
        return "generateJson";
    }
*/
    //Funktion wird aufgerufen, wenn "/download/{id}" mit Get geöffnet wird. Gibt eine FileSystemResource zurück, mit dem generierten File
    /*
    @GetMapping("/download/{id}")
    @ResponseBody
    public FileSystemResource downloadFile(@PathVariable String id) {
        FileHandler fileHandler = new FileHandler();
        File f = fileHandler.getFile(Integer.parseInt(id));
        return new FileSystemResource(f);
    }*/
/*
    @GetMapping("/getEndpoints/{file}/fillParameter")
    public String fillParameterGet(@PathVariable String file){
        return "wrongGet";
    }
    @GetMapping("/getEndpoints/{file}/fillParameter/generate")
    public String generateGet(@PathVariable String file){
        return "wrongGet";
    }

    @GetMapping("/getEndpoints/{file}/fillParameterJson")
    public String fillParameterJsonGet(@PathVariable String file){
        return "wrongGet";
    }
    @GetMapping("/getEndpoints/{file}/fillParameterJson/generateJson")
    public String generateJsonGet(@PathVariable String file){
        return "wrongGet";
    }


    public String decodeStringURL(String s){
        return java.net.URLDecoder.decode(s, StandardCharsets.UTF_8);
    }
*/


}