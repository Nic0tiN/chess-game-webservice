package com.clicktic.wsgamechess.Domain;

import com.clicktic.wsgamechess.Application.Command.MoveCommand;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@RedisHash("game")
public class GameInstance {
    @Id
    String id;
    @NonNull
    String whitePlayerName;
    @NonNull
    String blackPayerName;
    @Indexed
    @NonNull
    Set<MoveCommand> moves = new HashSet<>();
}
