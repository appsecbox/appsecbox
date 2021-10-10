package team.appsec.appsecbox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.appsec.appsecbox.domain.application.Dataset;
import team.appsec.appsecbox.domain.assessment.Control;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DictionaryController {

    @GetMapping("/api/dictionary/control/type")
    List<Control.Type> getControlTypes() {
        return Arrays.asList(Control.Type.values());
    }
    @GetMapping("/api/dictionary/dataset/category")
    List<Dataset.Category> getDatasetCategories() {
        return Arrays.asList(Dataset.Category.values());
    }

}
