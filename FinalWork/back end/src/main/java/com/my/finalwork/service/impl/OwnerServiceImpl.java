package com.my.finalwork.service.impl;

import com.my.finalwork.entity.Owner;
import com.my.finalwork.mappers.OwnerMapper;
import com.my.finalwork.service.IOwnerService;
import com.my.finalwork.service.ex.OwnerInfoDuplicatedException;
import com.my.finalwork.service.ex.OwnerNotFoundException;
import com.my.finalwork.service.ex.UnknownException;
import com.my.finalwork.service.ex.UserBoundDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

// owner类业务层实现
@Service
public class OwnerServiceImpl implements IOwnerService {
    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public void addOwner(Owner owner) {
        // 新对象中不需要id
        duplicationCheck(owner, owner);
        Integer row = ownerMapper.addOwner(owner);
        if (row != 1) {
            throw new UnknownException("添加业主信息时产生未知的异常");
        }
    }

    @Override
    public void updateOwner(Owner owner) {
        // 新的对象中需要id和更新的部分即可
        Integer oid = owner.getId();
        Owner existOwner = ownerMapper.getOwner(oid);
        if (existOwner == null) {
            throw new OwnerNotFoundException("业主信息不存在");
        } else {
            duplicationCheck(owner, existOwner);
            Integer row = ownerMapper.updateOwner(owner);
            if (row != 1) {
                throw new UnknownException("更新业主信息时产生未知的异常");
            }
        }
    }

    private void duplicationCheck(Owner owner, Owner existOwner) {
        // 检查更新的身份证号是否重复
        String idNumber = owner.getIDNumber();
        if (idNumber != null) {
            Owner duplicatedOwner = ownerMapper.getOwnerByID(idNumber);
            if (duplicatedOwner != null && !Objects.equals(duplicatedOwner.getId(), owner.getId())) {
                throw new OwnerInfoDuplicatedException("身份信息重复");
            }
        }
        // 检查更新的地址是否重复
        Integer buildingNumber = owner.getBuildingNumber();
        Integer floorNumber = owner.getFloorNumber();
        if (buildingNumber != null && floorNumber != null) {
            Owner duplicatedOwner = ownerMapper.getOwnerByLocation(buildingNumber, floorNumber);
            if (duplicatedOwner != null && !Objects.equals(duplicatedOwner.getId(), owner.getId())) {
                throw new OwnerInfoDuplicatedException("地址信息重复");
            }
        } else if (buildingNumber != null) {
            Owner duplicatedOwner = ownerMapper.getOwnerByLocation(buildingNumber, existOwner.getFloorNumber());
            if (duplicatedOwner != null && !Objects.equals(duplicatedOwner.getId(), owner.getId())) {
                throw new OwnerInfoDuplicatedException("地址信息重复");
            }
        } else if (floorNumber != null) {
            Owner duplicatedOwner = ownerMapper.getOwnerByLocation(existOwner.getBuildingNumber(), floorNumber);
            if (duplicatedOwner != null && !Objects.equals(duplicatedOwner.getId(), owner.getId())) {
                throw new OwnerInfoDuplicatedException("地址信息重复");
            }
        }
    }

    @Override
    public void deleteOwner(Integer oid) {
        Owner existOwner = ownerMapper.getOwner(oid);
        if (existOwner == null) {
            throw new OwnerNotFoundException("业主信息不存在");
        } else {
            Integer row = ownerMapper.deleteOwner(oid);
            if (row != 1) {
                throw new UnknownException("删除业主信息时产生未知的异常");
            }
        }
    }

    @Override
    public Owner getOwner(Integer oid) {
        Owner owner = ownerMapper.getOwner(oid);
        if (owner == null) {
            throw new OwnerNotFoundException("业主信息不存在");
        } else {
            return owner;
        }
    }

    @Override
    public Owner getOwnerByID(String IDNumber) {
        Owner owner = ownerMapper.getOwnerByID(IDNumber);
        if (owner == null) {
            throw new OwnerNotFoundException("业主信息不存在");
        } else {
            return owner;
        }
    }

    @Override
    public Owner getOwnerByLocation(Integer buildingNumber, Integer floorNumber) {
        Owner owner = ownerMapper.getOwnerByLocation(buildingNumber, floorNumber);
        if (owner == null) {
            throw new OwnerNotFoundException("业主信息不存在");
        } else {
            return owner;
        }
    }

    @Override
    public void boundUser(Integer oid, Integer uid) {
        // 绑定过程中的uid一定存在
        Owner existOwner = ownerMapper.getOwner(oid);
        if (existOwner == null) {
            throw new OwnerNotFoundException("业主信息不存在");
        } else {
            existOwner = ownerMapper.getOwnerByUser(uid);
            if (existOwner != null && !Objects.equals(existOwner.getId(), oid)) {
                throw new UserBoundDuplicatedException("用户重复绑定");
            } else {
                Integer row = ownerMapper.bounderUser(oid, uid);
                if (row != 1) {
                    throw new UnknownException("用户绑定过程中产生未知的异常");
                }
            }
        }
    }

    @Override
    public Owner getOwnerByUser(Integer uid) {
        Owner owner = ownerMapper.getOwnerByUser(uid);
        if (owner == null) {
            throw new OwnerNotFoundException("业主信息不存在");
        } else {
            return owner;
        }
    }
}
