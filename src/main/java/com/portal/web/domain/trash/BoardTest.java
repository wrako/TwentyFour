//        System.out.println("hello micha");

//        Account account = new Account();
//        account.setName("Lexor");
//        account.setPass("24124");
//        account.setGame("1024");
//
//        accountService.addUser(account);
//        ratingService.reset();
//
//        ratingService.setRating(new Rating(421L, 432, new Date(), "1024"));
//        ratingService.setRating(new Rating(441L, 123, new Date(), "1024"));
//        ratingService.setRating(new Rating(1L, 41, new Date(), "1024"));
//        ratingService.setRating(new Rating(4121L, 13, new Date(), "1024"));
//        ratingService.setRating(new Rating(31L, 51, new Date(), "1024"));
//        ratingService.setRating(new Rating(121L, 142, new Date(), "1024"));
//
//        System.out.println(ratingService.getAverageRating("1024"));
//        System.out.println(ratingService.getRating("1024", 421L));
//        ratingService.reset();

//        commentService.reset();
//
//        commentService.addComment(new Comment(421L, "Lorem212", new Date(), "1024"));
//        commentService.addComment(new Comment(422L, "setRating(new Rating(441L, 123, new", new Date(), "1024"));
//        commentService.addComment(new Comment(423L, " JPA EntityManagerFactory for pers", new Date(), "1024"));
//        commentService.addComment(new Comment(424L, "e: select r1_0.id,r1_0.game,r1_0.ra", new Date(), "1024"));
//        commentService.addComment(new Comment(425L, "исправить эту проблему, вам нужно убедиться, что в вашей базе данных нет дубликатов в таблице", new Date(), "1024"));
//        commentService.addComment(new Comment(426L, "niečo ako vyrovnávaciu pamäť.  PersistenceContext", new Date(), "1024"));
//        commentService.addComment(new Comment(426L, "EPIC COUNTRY | ''Do Right, Fear No Man'' by The Last Knife Fighter\n", new Date(), "1024"));
//        commentService.addComment(new Comment(427L, "LoreEPIC COUNTRY | ''Do Right, Fear No Man'' by The Last Knife Fighter\nm212", new Date(), "1024"));
//        commentService.addComment(new Comment(428L, "sk.tuke.gamestudio.SpringClient", new Date(), "1024"));
//
//        List<Comment> comments = commentService.getComments("1024");
//
//        for(Comment comment : comments){
//            System.out.println(comment.getComment());
//        }
//        System.out.println();
//
//        commentService.reset();

//        Score score = new Score();
//        score.setId(231L);
//        score.setPoints(3213);
//        score.setPlayedOn(new Date());
//        score.setGame("dsdds");
//
//        scoreService.addScore(score);
//
//        scoreService.addScore(new Score(421L, 432, new Date(), "1024"));
//        scoreService.addScore(new Score(1321L, 12412, new Date(), "1024"));
//        scoreService.addScore(new Score(1122L, 43, new Date(), "1024"));
//        scoreService.addScore(new Score(132L, 21, new Date(), "1024"));
//        scoreService.addScore(new Score(1332L, 43, new Date(), "1024"));
//        scoreService.addScore(new Score(134L, 21, new Date(), "1024"));
//
//        List<Score> scores = scoreService.getTopScores("1024");
//
//        for (Score score2 : scores){
//            System.out.println(score2.getPoints());
//        }

//        System.out.println(accountService.authorization(account).getName());
