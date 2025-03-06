package com.csq.fweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.csq.fweb.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController<T> {

    private IService<T> baseService;

    public BaseController(IService<T> baseService) {
        this.baseService = baseService;
    }

    @Operation(summary = "获取所有记录信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取所有记录信息",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class,
                                            subTypes = {List.class},
                                            description = "通用返回结果，包含记录信息列表"))
                    }),
            @ApiResponse(responseCode = "500", description = "查询失败",
                    content = @Content)
    })
    @GetMapping
    public Result<List<T>> findAll() {
        try {
            List<T> list = baseService.list();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败: " + e.getMessage());
        }
    }

    @Operation(summary = "根据 ID 获取记录信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取记录信息",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class,
                                            description = "通用返回结果，包含单条记录信息"))
                    }),
            @ApiResponse(responseCode = "404", description = "未找到对应 ID 的记录信息",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "查询失败",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Result<T> findById(@Parameter(description = "记录 ID") @PathVariable Serializable id) {
        try {
            T entity = baseService.getById(id);
            if (entity != null) {
                return Result.success(entity);
            } else {
                return Result.error(HttpStatus.NOT_FOUND.value(), "未找到对应 ID 的记录信息");
            }
        } catch (Exception e) {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败: " + e.getMessage());
        }
    }

    @Operation(summary = "新增记录信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "记录信息新增成功",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class,
                                            description = "通用返回结果，包含新增操作结果"))
                    }),
            @ApiResponse(responseCode = "400", description = "请求参数错误",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "新增失败",
                    content = @Content)
    })
    @PostMapping
    public Result<Boolean> insert(@Parameter(description = "记录实体信息") @RequestBody T entity) {
        try {
            boolean success = baseService.save(entity);
            return Result.success(success);
        } catch (Exception e) {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "新增失败: " + e.getMessage());
        }
    }

    @Operation(summary = "更新记录信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "记录信息更新成功",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class,
                                            description = "通用返回结果，包含更新操作结果"))
                    }),
            @ApiResponse(responseCode = "400", description = "请求参数错误",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "未找到对应 ID 的记录信息",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "更新失败",
                    content = @Content)
    })
    @PutMapping
    public Result<Boolean> update(@Parameter(description = "记录实体信息") @RequestBody T entity) {
        try {
            boolean success = baseService.updateById(entity);
            if (success) {
                return Result.success(success);
            } else {
                return Result.error(HttpStatus.NOT_FOUND.value(), "未找到对应 ID 的记录信息");
            }
        } catch (Exception e) {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败: " + e.getMessage());
        }
    }

    @Operation(summary = "根据 ID 删除记录信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "记录信息删除成功",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class,
                                            description = "通用返回结果，包含删除操作结果"))
                    }),
            @ApiResponse(responseCode = "404", description = "未找到对应 ID 的记录信息",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "删除失败",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@Parameter(description = "记录 ID") @PathVariable Serializable id) {
        try {
            boolean success = baseService.removeById(id);
            if (success) {
                return Result.success(success);
            } else {
                return Result.error(HttpStatus.NOT_FOUND.value(), "未找到对应 ID 的记录信息");
            }
        } catch (Exception e) {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除失败: " + e.getMessage());
        }
    }


    @Operation(summary = "按实体类字段进行精确查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class,
                                            subTypes = {List.class},
                                            description = "通用返回结果，包含查询到的记录信息列表"))
                    }),
            @ApiResponse(responseCode = "500", description = "查询失败",
                    content = @Content)
    })

    @PostMapping("/query")
    public Result<List<T>> queryByFields(@Parameter(description = "查询条件，以 JSON 格式传入实体类对象") @RequestBody T entity) {
        try {
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null) {
                    queryWrapper.eq(field.getName(), value);
                }
            }
            List<T> list = baseService.list(queryWrapper);
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败: " + e.getMessage());
        }
    }

    @Operation(summary = "按实体类字段进行模糊查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class,
                                            subTypes = {List.class},
                                            description = "通用返回结果，包含查询到的记录信息列表"))
                    }),
            @ApiResponse(responseCode = "500", description = "查询失败",
                    content = @Content)
    })
    @PostMapping("/queryLike")
    public Result<List<T>> queryByFieldsLike(@Parameter(description = "查询条件，以 JSON 格式传入实体类对象") @RequestBody T entity) {
        try {
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value != null && value instanceof String) {
                    // 只对字符串类型的字段进行模糊查询
                    queryWrapper.like(field.getName(), value);
                }
            }
            List<T> list = baseService.list(queryWrapper);
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败: " + e.getMessage());
        }
    }

}