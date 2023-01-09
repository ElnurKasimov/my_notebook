package group_project.MyNotebook.note;

import group_project.MyNotebook.user.UserDto;
import group_project.MyNotebook.user.UserService;
import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
@RequiredArgsConstructor
@Controller
@RestController
@RequestMapping("/note")
public class NoteController {
    private final String NOTE_LIST_PAGE = "/note/list";
    private final NoteService noteService;
    private final UserService userService;

    @GetMapping("/list")
    public ModelAndView getNotes() {
        ModelAndView notes = new ModelAndView("notes");
        try {
            UserDto user = userService.findByEmail(getUserEmail());
            List<NoteDto> notesList = (user.getRoles().get(0).getName().equals("ROLE_ADMIN"))
                    ? noteService.findAllPublicAndAdminNotes()
                    : noteService.findAll(user);
            notes.addObject("notesList", notesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }

    @GetMapping("/create")
    public ModelAndView newNoteForm(Model model) {
        NoteDto noteDto = new NoteDto();
        noteDto.setAccess(Access.PRIVATE);
        model.addAttribute("note", noteDto);
        return new ModelAndView("createNote");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editNoteForm(@PathVariable("id") UUID id) {
        ModelAndView editNote = new ModelAndView("editNote");
        try {
            NoteDto note = noteService.get(id);
            note.setHtml(markdownToHTML(note.getContent()));
            editNote.addObject("note", note);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editNote;
    }

    @PostMapping("/create")
    public RedirectView saveNote(@ModelAttribute("note") NoteDto note) {
        try {
            UserDto userDto = userService.findByEmail(getUserEmail());
            note.setHtml(markdownToHTML(note.getContent()));
            note.setUser(userDto);
            noteService.create(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView(NOTE_LIST_PAGE);
    }

    @GetMapping("/delete/{id}")
    public RedirectView removeNote(@PathVariable("id") UUID id) {
        try {
            noteService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RedirectView(NOTE_LIST_PAGE);
    }

    @GetMapping("/share/{id}")
    public ModelAndView getSharedNote(@PathVariable("id") UUID id) {
        ModelAndView share = new ModelAndView("sharedNote");
        NoteDto note = noteService.get(id);
        if (note == null || note.getAccess().equals(Access.PRIVATE)) {
            share.addObject("message", "Такої нотатки не існує :(");
        } else if (note.getAccess().equals(Access.PUBLIC)) {
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

    private String getUserEmail() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication().getName();
    }
}
