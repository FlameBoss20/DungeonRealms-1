package net.dungeonrealms.common.network;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * Class written by APOLLOSOFTWARE.IO on 7/12/2016
 */

public enum ShardInfo
{

    // SHARD //
    US1("US-1", "us1", new ServerAddress("45.55.50.142", 25565));


    @Getter
    private final String shardID;

    @Getter
    private final String pseudoName;

    @Getter
    private final ServerAddress address;

    ShardInfo(String shardID, String pseudoName, ServerAddress address)
    {
        this.shardID = shardID;
        this.pseudoName = pseudoName;
        this.address = address;
    }


    public static ShardInfo getByPseudoName(String pseudoName)
    {
        Optional<ShardInfo> query = Arrays.stream(ShardInfo.values()).
                filter(info -> info.getPseudoName().equals(pseudoName)).findFirst();

        return query.isPresent() ? query.get() : null;
    }

    public static ShardInfo getByShardID(String shardID)
    {
        Optional<ShardInfo> query = Arrays.stream(ShardInfo.values()).
                filter(info -> info.getShardID().equals(shardID)).findFirst();

        return query.isPresent() ? query.get() : null;
    }

    public static ShardInfo getByAddress(ServerAddress address)
    {
        Optional<ShardInfo> query = Arrays.stream(ShardInfo.values()).
                filter(info -> info.getAddress().toString().equals(address.toString())).findFirst();

        return query.isPresent() ? query.get() : null;
    }
}
