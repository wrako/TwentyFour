//package sk.tuke.gamestudio;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.client.RestTemplate;
//import sk.tuke.gamestudio.game.Game;
//import sk.tuke.gamestudio.service.*;
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
//        pattern = "sk.tuke.gamestudio.server.*"))
//@SpringBootApplication
//public class SpringClient {
//
//
//    public static void main(String[] args) {
//
//        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Bean
//    public CommandLineRunner runner(Game game) {
//        return args -> game.menu();
//    }
//
//    @Bean
//    public Game game() {
//        return new Game();
//    }
//
//    @Bean
//    public AccountService accountService() {
//        return new AccountServiceRestClient();
////        return new AccouSpringBootApplicationntServiceJPA();
//    }
//
//    @Bean
//    public ScoreService scoreService() {
////        return new ScoreServiceJPA();
//        return new ScoreServiceRestClient();
//
//    }
//
//    @Bean
//    public RatingService ratingService() {
////        return new RatingServiceJPA();
//        return new RatingServiceRestClient();
//    }
//
//    @Bean
//    public CommentService commentService() {
//      //  return new CommentServiceJPA();
//        return new CommentServiceRestClient();
//    }
//}
