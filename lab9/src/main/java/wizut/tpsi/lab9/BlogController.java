package wizut.tpsi.lab9;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {

	@Autowired
	BlogRepository blogRepository;

	@RequestMapping("/")
	public String home(Model model) throws SQLException {
		List<BlogPost> blogPostList = blogRepository.getAllPosts();

		model.addAttribute("blogPosts", blogPostList);

		return "index";
	}

	@PostMapping("/newpost")
	public String newPost(BlogPost post) throws SQLException {
		blogRepository.createPost(post);
		return "redirect:/";
	}

	@PostMapping("/deletepost")
	public String deletePost(long id) throws SQLException {
		blogRepository.deletePost(id);
		return "redirect:/";
	}

}