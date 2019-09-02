package relipa.religram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import relipa.religram.custom_repository.HashtagRepository;
import relipa.religram.entity.Hashtag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HashtagServiceImpl {
    @Autowired
    private HashtagRepository hashtagRepository;

    @Transactional(rollbackFor = Exception.class)
    public void saveNewHashtag(List<String> hashtags) {
        hashtags.forEach(ht -> {
            if (hashtagRepository.findByHashtag(ht.toLowerCase()) == null) {
                hashtagRepository.save(new Hashtag(ht.toLowerCase(), LocalDateTime.now(), new ArrayList<>()));
            }
        });
    }

    public List<Hashtag> findByHashtag(List<String> hashtags) {
        return hashtags.stream().map(s -> hashtagRepository.findByHashtag(s.toLowerCase())).collect(Collectors.toList());
    }
}
