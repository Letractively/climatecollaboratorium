package org.climatecollaboratorium.facelets.discussions.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class DiscussionServiceMock {
    private static Random rand = new Random();
    private static int CATEGORIES_COUNT = 50;
    private static int THREADS_COUNT_MIN = 5;
    private static int THREADS_COUNT_MAX = 15;
    private static int MESSAGES_COUNT_MIN = 5;
    private static int MESSAGES_COUNT_MAX = 15;
    
    private static List<MessageWrapper> messages = new ArrayList<MessageWrapper>();
    private static List<CategoryWrapper> categories = new ArrayList<CategoryWrapper>();
    private static List<ThreadWrapper> threads = new ArrayList<ThreadWrapper>();
    
    private static Map<Long, MessageWrapper> messagesById = new HashMap<Long, MessageWrapper>();
    private static Map<Long, CategoryWrapper> categoriesById = new HashMap<Long, CategoryWrapper>();
    private static Map<Long, ThreadWrapper> threadsById = new HashMap<Long, ThreadWrapper>();
    private static Map<Long, ThreadWrapper> threadsByCategoryId = new HashMap<Long, ThreadWrapper>();
    private static Map<Long, MessageWrapper> messagesByThreadId = new HashMap<Long, MessageWrapper>();
    
    private static final String[] paragraphs = {
        "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum enim lectus, molestie nec auctor nec, gravida et libero. Mauris vulputate sagittis sagittis. Nulla ac metus tortor. Suspendisse quis nisl quis risus fringilla facilisis. Pellentesque eget diam sed metus varius sollicitudin quis nec ipsum. Aliquam erat volutpat. Quisque ut turpis lorem. Etiam eget neque ut est pellentesque gravida non eget neque. Phasellus justo ipsum, vehicula et ullamcorper ut, elementum tempor libero. Aliquam erat volutpat. Phasellus tincidunt, nisi non interdum posuere, enim massa sollicitudin turpis, in tristique lacus leo accumsan purus. Sed dictum libero malesuada mauris hendrerit eget viverra sapien tincidunt. Etiam cursus commodo orci, vitae molestie tellus sollicitudin et. In ut egestas libero. Sed vehicula tortor eget leo adipiscing luctus placerat risus auctor. In nunc justo, commodo ac lacinia at, tincidunt eu dolor.</p>",
        "<p>Nam semper orci sed enim condimentum ullamcorper scelerisque nunc pretium. Aliquam eget urna lorem. Sed sit amet mauris sed turpis cursus interdum. Pellentesque lacinia vehicula egestas. In accumsan elit quis enim rhoncus scelerisque. Morbi consequat tincidunt mauris porttitor porta. Sed pellentesque, lectus id egestas dignissim, massa mauris adipiscing mi, ac malesuada eros nisl at libero. Morbi tempus quam non tellus ornare pharetra. Pellentesque faucibus suscipit velit ut sodales. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec nec tortor orci, sit amet blandit velit. Duis ut felis eget ipsum ornare euismod et a mauris. Curabitur pretium, nisl eget lobortis aliquet, turpis justo hendrerit ligula, quis lobortis ante quam sit amet metus. Morbi aliquet ornare dui, quis elementum mi imperdiet vel. Nam eu nisl turpis. In gravida, ipsum ac lobortis accumsan, tellus erat ultrices tellus, ac sagittis turpis augue fringilla augue. Nulla eget velit ipsum, vel posuere neque. Morbi in viverra neque. Praesent vitae dolor lorem.</p>",
        "<p>Sed at dapibus dui. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut in justo aliquet arcu sollicitudin egestas ut in augue. Quisque in est quis nisl hendrerit consectetur. Nam pretium, turpis consequat imperdiet fringilla, nunc dolor egestas orci, quis scelerisque mauris lacus quis risus. Suspendisse consectetur arcu at odio dictum ornare. Etiam vel sapien orci, vitae tincidunt magna. Mauris sed ligula risus, in fermentum orci. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris rhoncus magna ut augue sodales vel sollicitudin est posuere. Morbi ac tincidunt erat. Integer eget velit ac nulla pharetra aliquet. Vivamus mollis imperdiet quam eu sollicitudin. Praesent condimentum laoreet purus, ac fermentum justo posuere euismod. In hac habitasse platea dictumst.</p>",
        "<p>Nunc non diam nec mi ornare semper at sit amet est. Etiam faucibus feugiat risus, eu blandit quam vehicula fermentum. Phasellus sed sapien lorem. Morbi vel metus enim. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nunc suscipit, mauris a consectetur aliquam, dui lacus venenatis odio, at sagittis enim lacus vel nunc. Pellentesque erat arcu, aliquet sit amet ornare vel, pellentesque eu urna. Curabitur hendrerit dolor dui, non dignissim lectus. Suspendisse potenti. Aenean leo leo, sagittis dignissim sodales ut, dapibus eget arcu. Aenean posuere, augue sed pharetra porttitor, lacus ipsum imperdiet ipsum, ut euismod ligula mi vitae elit. Nunc eget sapien eget odio tincidunt lacinia. Aliquam fermentum turpis ut ipsum iaculis lacinia.</p>",
        "<p>Curabitur semper bibendum turpis, non tincidunt felis laoreet sit amet. Cras blandit, tellus et elementum ornare, purus diam suscipit nulla, ac tempor massa lacus a augue. Nulla porta ipsum non urna facilisis pellentesque. Ut suscipit, urna eu fringilla euismod, nisl felis aliquet magna, vel pulvinar neque nunc at nulla. Aenean vestibulum quam non quam gravida sed cursus sapien ornare. Fusce orci nisl, pharetra eu euismod quis, tristique eget metus. Aliquam vitae ligula justo, ut pellentesque nunc. Pellentesque id augue quis nisl gravida accumsan ac at orci. Fusce non velit arcu, quis tristique nulla. Praesent rhoncus dictum facilisis. Fusce porttitor molestie ultrices. Phasellus quis sem nec est mollis aliquet. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>"
    };
    
    private static final String[] sentences = {
        "Sed dictum libero malesuada mauris hendrerit eget viverra sapien tincidunt.", 
        "Etiam cursus commodo orci, vitae molestie tellus sollicitudin et. In ut egestas libero.",
        "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.", 
        "Donec nec tortor orci, sit amet blandit velit.",
        "Duis ut felis eget ipsum ornare euismod et a mauris.",
        "Curabitur pretium, nisl eget lobortis aliquet, turpis justo hendrerit ligula, quis lobortis ante quam sit amet metus.", 
        "Morbi aliquet ornare dui, quis elementum mi imperdiet vel.",
        "Nam eu nisl turpis. In gravida, ipsum ac lobortis accumsan, tellus erat ultrices tellus, ac sagittis turpis augue fringilla augue.", 
        "Nulla eget velit ipsum, vel posuere neque."
    };

    private static final Long[] userIds = { 10144L, 22964L, 10115L, 21112L };
    private static User[] users = new User[0];
    
    static {
        try {
            users = new User[userIds.length];
            for (int i=0; i < userIds.length; i++) {
                users[i] = UserLocalServiceUtil.getUser(userIds[i]);
            }
        }
        catch (Throwable e) {
            // ignore
        }
        generate();
    }

    private static void generate() {
        int categoriesCount = CATEGORIES_COUNT;
        
        long id = 0;
        for (int ic = 0; ic < categoriesCount; ic++) {
            int threadsCount = THREADS_COUNT_MIN + rand.nextInt(THREADS_COUNT_MAX - THREADS_COUNT_MIN);
            CategoryWrapper category = new CategoryWrapper(id++, getRandomUser(), getRandomSentence(), getRandomParagraph());
            categories.add(category);
            categoriesById.put(category.getId(), category);
            
            for (int it = 0; it < threadsCount; it++) {
                int messagesCount = MESSAGES_COUNT_MIN + rand.nextInt(MESSAGES_COUNT_MAX - MESSAGES_COUNT_MIN);
                ThreadWrapper thread = new ThreadWrapper(id++, getRandomUser(), getRandomSentence(), getRandomParagraphs());
                threads.add(thread);
                threadsById.put(thread.getId(), thread);
                threadsByCategoryId.put(category.getId(), thread);
                category.addThread(thread);
                thread.setCategory(category);
                
                for (int im = 0; im < messagesCount; im++) {
                    MessageWrapper message = new MessageWrapper(id++, getRandomUser(), getRandomSentence(), getRandomParagraphs());
                    
                    messages.add(message);
                    messagesById.put(message.getId(), message);
                    messagesByThreadId.put(thread.getId(), message);
                    thread.addMessage(message);
                    message.setThread(thread);
                }
            }
            
        }
    }
    
    public static User getRandomUser() {
        return users[rand.nextInt(users.length)];
    }
    
    private static String getRandomSentence() {
        return sentences[rand.nextInt(sentences.length)];
    }
    
    private static String getRandomParagraph() {
        return paragraphs[rand.nextInt(paragraphs.length)];
    }
    
    private static String getRandomParagraphs() {
        int paragraphCount = 1 + rand.nextInt(3);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < paragraphCount; i++) {
            sb.append(getRandomParagraph());
        }
        return sb.toString();
    }
    
    
    public static List<CategoryWrapper> getCategories() {
        return categories;
    }
    
    public static List<MessageWrapper> getMessages() {
        return messages;
    }
    
    public static List<ThreadWrapper> getThreads() {
        return threads;
    }

    public static ThreadWrapper getThreadById(Long threadId) {
        return threadsById.get(threadId);
    }

    public static CategoryWrapper getCategoryById(Long categoryId) {
        return categoriesById.get(categoryId);
    }
    
    
}
