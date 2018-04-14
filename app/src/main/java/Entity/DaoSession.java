package Entity;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import Entity.User;
import Entity.Height;
import Entity.Weight;
import Entity.LeftEye;
import Entity.RightEye;
import Entity.BloodType;
import Entity.BloodPre;

import Entity.UserDao;
import Entity.HeightDao;
import Entity.WeightDao;
import Entity.LeftEyeDao;
import Entity.RightEyeDao;
import Entity.BloodTypeDao;
import Entity.BloodPreDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig heightDaoConfig;
    private final DaoConfig weightDaoConfig;
    private final DaoConfig leftEyeDaoConfig;
    private final DaoConfig rightEyeDaoConfig;
    private final DaoConfig bloodTypeDaoConfig;
    private final DaoConfig bloodPreDaoConfig;

    private final UserDao userDao;
    private final HeightDao heightDao;
    private final WeightDao weightDao;
    private final LeftEyeDao leftEyeDao;
    private final RightEyeDao rightEyeDao;
    private final BloodTypeDao bloodTypeDao;
    private final BloodPreDao bloodPreDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        heightDaoConfig = daoConfigMap.get(HeightDao.class).clone();
        heightDaoConfig.initIdentityScope(type);

        weightDaoConfig = daoConfigMap.get(WeightDao.class).clone();
        weightDaoConfig.initIdentityScope(type);

        leftEyeDaoConfig = daoConfigMap.get(LeftEyeDao.class).clone();
        leftEyeDaoConfig.initIdentityScope(type);

        rightEyeDaoConfig = daoConfigMap.get(RightEyeDao.class).clone();
        rightEyeDaoConfig.initIdentityScope(type);

        bloodTypeDaoConfig = daoConfigMap.get(BloodTypeDao.class).clone();
        bloodTypeDaoConfig.initIdentityScope(type);

        bloodPreDaoConfig = daoConfigMap.get(BloodPreDao.class).clone();
        bloodPreDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        heightDao = new HeightDao(heightDaoConfig, this);
        weightDao = new WeightDao(weightDaoConfig, this);
        leftEyeDao = new LeftEyeDao(leftEyeDaoConfig, this);
        rightEyeDao = new RightEyeDao(rightEyeDaoConfig, this);
        bloodTypeDao = new BloodTypeDao(bloodTypeDaoConfig, this);
        bloodPreDao = new BloodPreDao(bloodPreDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Height.class, heightDao);
        registerDao(Weight.class, weightDao);
        registerDao(LeftEye.class, leftEyeDao);
        registerDao(RightEye.class, rightEyeDao);
        registerDao(BloodType.class, bloodTypeDao);
        registerDao(BloodPre.class, bloodPreDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        heightDaoConfig.clearIdentityScope();
        weightDaoConfig.clearIdentityScope();
        leftEyeDaoConfig.clearIdentityScope();
        rightEyeDaoConfig.clearIdentityScope();
        bloodTypeDaoConfig.clearIdentityScope();
        bloodPreDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public HeightDao getHeightDao() {
        return heightDao;
    }

    public WeightDao getWeightDao() {
        return weightDao;
    }

    public LeftEyeDao getLeftEyeDao() {
        return leftEyeDao;
    }

    public RightEyeDao getRightEyeDao() {
        return rightEyeDao;
    }

    public BloodTypeDao getBloodTypeDao() {
        return bloodTypeDao;
    }

    public BloodPreDao getBloodPreDao() {
        return bloodPreDao;
    }

}