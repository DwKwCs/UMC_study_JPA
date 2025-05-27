package umc.spring.service.MissionService;

public interface MissionQueryService {
    boolean isMissionChallengeable(Long user, Long mission);
}
