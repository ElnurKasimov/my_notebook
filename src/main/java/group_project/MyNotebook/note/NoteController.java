package group_project.MyNotebook.note;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/note")
public class NoteController {

    @GetMapping("/list")
    public ModelAndView getNotes() {
        ModelAndView note = new ModelAndView("note");
        //Temporary used in-memory notes list
        List<NoteDto> noteDtoList = List.of(
                new NoteDto(UUID.randomUUID(), "заметка 1",
                        "тут какой-то тестовый тескт14внег ",
                        "private"),
                new NoteDto(UUID.randomUUID(), "имя 1",
                        "тут какой-то тестовый тескт ввпкпкгнру еукеукекуе ыые кеве уе  кепвае ке",
                        "public"),
                new NoteDto(UUID.randomUUID(), "тестовое имя",
                        "в анк н нн укнук нн5ун6457н 457рарвр",
                        "private"),
                new NoteDto(UUID.randomUUID(), "аривапьто56",
                        "впеолимв енгрпмз 8тьйвпеоли мвенгрпмз8ть йвпеолимве нгрпм з8тьйв пеолимве нгрпмз8 тьйвпеол имвенгрп мз8тьй" +
                                "вп еоли мвенгрпм з8тьйвпе олимвенгрпм з8тьйвпе олимве нг рпмз8 тьй впеолимв енгрп мз8тьйвпе олимвенгр пмз8тьй" +
                                "впеол имвенгрп мз8тьй впеол имвенгрпм з8ть йвпе олимвенгр пмз8тьй впеоли мвенгрп мз8т ьйвпе олимве нгрпм з8тьй" +
                                "впеол имве нгр пмз8ть йвпеолим венгрпм з8тьйвп еоли мвен грпмз8тьй впеоли мвенгрп мз8т ьйвпеоли венгрп мз8тьй",
                        "private")
        );
        try {
            //TODO implement code below
            //noteService.getAll()
            //List<NotesDto> notesList = noteService.getAll();
            note.addObject("notesList", noteDtoList);
        } catch (Exception e) {
            e.printStackTrace();
            return note.addObject("errorMessage", e.getMessage());
        }
        return note;
    }

    @GetMapping("/create")
    public ModelAndView newNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return new ModelAndView("createNote");
    }

    @PostMapping("/create")
    public ModelAndView save(Note note, Model model) {
        note.setHtml(markdownToHTML(note.getContent()));
        model.addAttribute("note", note);
        return new ModelAndView("savedNote");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editNoteForm(@PathVariable("id") UUID id) {
        ModelAndView editNote = new ModelAndView("editNote");
        //savedNote = service find note by id
        //savedNote.setHtml(markdownToHTML(post.getContent()));
        //editNote.addObject("savedNote", savedNote);
        return editNote;
    }


    @GetMapping("/share/{id}")
    public ModelAndView getSharedNote(@PathVariable("id") UUID id) {
        ModelAndView share = new ModelAndView("sharedNote");
        try {
            //share.addObject("note", noteService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("error");
        }
        return share;
    }

    private String markdownToHTML(String markdown) {
        Parser parser = Parser.builder()
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .build();
        return renderer.render(document);
    }
}
