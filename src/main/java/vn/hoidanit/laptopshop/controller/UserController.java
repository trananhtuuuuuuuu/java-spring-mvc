package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;


@Controller
public class UserController {

  private final UserService userService;
  

  public UserController(UserService userService) {
         this.userService = userService;
  }


  @RequestMapping("/")
  public String getHomePage(Model model){
    List<User> arrUsers = this.userService.getAllUsersByEmail("1@gmail.com");
    System.out.println(arrUsers);
    model.addAttribute("eric", "test");
    model.addAttribute("hoidanit", "from controller with model");
    return "hello";
  }


  @RequestMapping("admin/user") // RequestMapping mean get data, so default method is GET
  public String getUserPage(Model model){
    List<User> users = this.userService.getAllUsers();
    model.addAttribute("users", users);
    return "admin/user/tableUser";
   
  }


  @RequestMapping("admin/user/{id}") // RequestMapping mean get data, so default method is GET
  public String getUserDetailPage(Model model,
  @PathVariable long id){
    System.out.println("check path id = " + id);
    //model.addAttribute("newUser", new User()); // Hàm tạo User() mặc định của class User trong domain(model)
    User user = this.userService.getUserById(id);
    model.addAttribute("user", user);
    model.addAttribute("id", id);
    
    return "admin/user/show";
   
  }


  // đây là hàm sau khi nhấn button create user ở
  // góc bên phải của trang admin/user
  @RequestMapping("/admin/user/create") // RequestMapping mean get data, so default method is GET
  public String createUserPage(Model model){
    model.addAttribute("newUser", new User()); // Hàm tạo User() mặc định của class User trong domain(model)
    return "admin/user/create";
   
  }



  // đây là hàm sau khi nhấn nút create để tạo mới một user thì dữ liệu chúng ta nhập vào ở các ô input sẽ được gửi qua hàm này
  // gửi cho ai?, thì nó gửi lên server và để lấy được dữ liệu ta dùng anotation có tên ModelAttribute() tham số truyền vào là tên 
  // modelAttribute 
  // mà ta đã đặt tên ở bên dạng form ở file html (create.jsp) hãy tưởng tượng cái modelAttribute có newUser đó là một object và dữ liệu
  // sau khi nhập trong form đó là các thuộc tín, và để lưu object với các thuộc tính đó thì ta đã tạo class có tên là User với các thuộc
  // tính tương ứng để chứa các dữ liệu đó, còn tên object thể hiện cho class mà chứa dữ liệu sau khi được nhập vào từ người dùng là hoidanit
  // Dữ liệu này sau khi nhập vào thì chúng ta cần phải dùng hệ quản trị cơ sở dữ liệu để lưu lại, chứ không mỗi lần f5 lại là chúng ta sẽ 
  // mất hết dữ liệu
  @RequestMapping(value = "/admin/user/create", method=RequestMethod.POST) // RequestMapping mean get data, so default method is GET
  public String createUserPage(Model model, 
  @ModelAttribute("newUser") User hoidanit){

    System.out.println(" run here " + hoidanit);
    this.userService.handleSaveUser(hoidanit);
    model.addAttribute("newUser", new User()); // Hàm tạo User() mặc định của class User trong domain(model)
    return "redirect:/admin/user";
   
  }

  @RequestMapping("/admin/user/update/{id}") // RequestMapping mean get data, so default method is GET
  public String getUpdateUser(Model model,
  @PathVariable long id){
    
    User currentUser = this.userService.getUserById(id);

    model.addAttribute("newUser", currentUser);
    return "admin/user/updateUser";
   
  }



  @PostMapping("/admin/user/update") // RequestMapping mean get data, so default method is GET
  public String updateUser(Model model,
  @ModelAttribute("newUser") User hoidanit){
    //System.out.println(hoidanit);
    User currentUser = this.userService.getUserById(hoidanit.getId());
    if(currentUser != null){
      currentUser.setAddress(hoidanit.getAddress());
      currentUser.setFullName(hoidanit.getFullName());
      currentUser.setPhone(hoidanit.getPhone());
      this.userService.handleSaveUser(currentUser);
    }
    
    //model.addAttribute("newUser", currentUser);

    return "redirect:/admin/user";
   
  }



  @GetMapping("admin/user/delete/{id}") // RequestMapping mean get data, so default method is GET
  public String deleteUserDetail(Model model,
  @PathVariable long id){
    
    //model.addAttribute("newUser", new User()); // Hàm tạo User() mặc định của class User trong domain(model)
    
    model.addAttribute("id", id);
    User user = new User();
    user.setId(id);
    model.addAttribute("newUser", user);
    
    return "admin/user/delete";
   
  }


  @PostMapping("admin/user/delete") // RequestMapping mean get data, so default method is GET
  public String postDeleteUserDetail(Model model,
  @ModelAttribute("newUser") User eric){
    
    this.userService.deleteUserById(eric.getId());
    
    return "redirect:/admin/user";
   
  }


  

}

