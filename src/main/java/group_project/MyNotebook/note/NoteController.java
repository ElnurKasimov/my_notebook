package group_project.MyNotebook.note;

import group_project.MyNotebook.note.Access;
import group_project.MyNotebook.note.NoteDto;
import group_project.MyNotebook.note.NoteService;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RestController
@RequestMapping("/note")
public class NoteController {
    private final String NOTE_LIST_PAGE = "/note/list";
    private final NoteService service;

    @GetMapping("/list")
    public ModelAndView getNotes() {
        ModelAndView notes = new ModelAndView("notes");
        try {
            notes.addObject("notesList", service.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }

    @GetMapping("/create")
    public ModelAndView newNoteForm(Model model) {
        model.addAttribute("note", new NoteDto());
        return new ModelAndView("createNote");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editNoteForm(@PathVariable("id") UUID id) {
        ModelAndView editNote = new ModelAndView("editNote");
        try {
            NoteDto note = service.get(id);
            note.setHtml(markdownToHTML(note.getContent()));
            editNote.addObject("note", note);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editNote;
    }

    @PostMapping("/create")
    public RedirectView saveNote(@ModelAttribute("note") NoteDto note) {
        note.setHtml(markdownToHTML(note.getContent()));
        try {
            service.create(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView(NOTE_LIST_PAGE);
    }

    @GetMapping("/delete/{id}")
    public RedirectView removeNote(@PathVariable("id") UUID id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView(NOTE_LIST_PAGE);
    }

    @GetMapping("/share/{id}")
    public ModelAndView getSharedNote(@PathVariable("id") UUID id) {
        ModelAndView share = new ModelAndView("sharedNote");
        NoteDto note = service.get(id);
        if(note == null || note.getAccess().equals(Access.PRIVATE)){
            share.addObject("message", "Такой заметки не существует :(");
        }
        else if (note.getAccess().equals(Access.PUBLIC)) {
            share.addObject("note", note);
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
