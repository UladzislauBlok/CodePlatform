package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.model.CodeSniped;

import java.util.List;

public interface CodeRepository extends JpaRepository<CodeSniped, String> {
    List<CodeSniped> findTop10ByLeftTimeSecAndLeftViewsOrderByLocalDateTimeDesc(int leftTimeSec, int leftViews);
}
