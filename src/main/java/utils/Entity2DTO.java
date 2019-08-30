package utils;

import relipa.religram.dto.*;
import relipa.religram.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class Entity2DTO {
    public static UserModel toUserModel(User user) {
        return new UserModel(user.getId(), user.getFullname(), user.getEmail(), user.getUsername(), user.getAvatar());
    }

    public static MentionModel toMentionModel(Mention mention) {
        UserModel userModel = toUserModel(mention.getUser());
        CommentModel commentModel = toCommentModel(mention.getComment());
        return new MentionModel(mention.getId(), userModel, commentModel, mention.getCreateAt());
    }

    public static LikeModel toLikeModel(Like like) {
        return new LikeModel(like.getId(), toUserModel(like.getUser()), like.getTimeStamp());
    }

    public static CommentModel toCommentModel(Comment comment) {
        UserModel userModel = toUserModel(comment.getUser());
        List<HashtagModel> hashtagModels = comment.getHashtags().stream().map(Entity2DTO::toHashtagModel)
                .collect(Collectors.toList());
        List<MentionModel> mentionModels = comment.getMentions().stream().map(Entity2DTO::toMentionModel)
                .collect(Collectors.toList());
        return new CommentModel(
                comment.getId(),
                userModel,
                comment.getContent(),
                comment.getCreateAt(),
                comment.getUpdateAt(),
                hashtagModels,
                mentionModels);
    }

    public static HashtagModel toHashtagModel(Hashtag hashtag) {
        return new HashtagModel(hashtag.getId(), hashtag.getHashtag(), hashtag.getTimeStamp());
    }

    public static PostModel toPostModel(Post post) {
        UserModel userModel = toUserModel(post.getUser());
        List<CommentModel> commentModels = post.getComments().stream().map(Entity2DTO::toCommentModel)
                .collect(Collectors.toList());
        List<LikeModel> likeModels = post.getLikes().stream().map(Entity2DTO::toLikeModel)
                .collect(Collectors.toList());
        return new PostModel(
                post.getId(),
                post.getContent(),
                userModel,
                commentModels,
                post.getPhotos(),
                likeModels,
                post.getCreateAt(),
                post.getUpdateAt());
    }
}
