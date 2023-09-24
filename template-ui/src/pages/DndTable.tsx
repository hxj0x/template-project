import { DeleteOutlined, MenuOutlined, PlusOutlined } from "@ant-design/icons";
import type { DragEndEvent } from "@dnd-kit/core";
import { DndContext, MouseSensor, useSensor, useSensors } from "@dnd-kit/core";
import { restrictToVerticalAxis } from "@dnd-kit/modifiers";
import {
    SortableContext,
    arrayMove,
    useSortable,
    verticalListSortingStrategy,
} from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";

import { AutoComplete, Button, Checkbox, Form, Input, Space, Table } from "antd";
import { ColumnsType } from "antd/es/table";
import React, { useCallback, useState } from "react";

interface DataType {
    key: string;
    name: string;
    dataType:
        | string
        | "varchar(255)"
        | "varchar(64)"
        | "int"
        | "bigint"
        | "datetime"
        | "date"
        | "time"
        | "bit(1)";
    isPk?: boolean;
    isAutoIncrement?: boolean;
    description?: string;
    handleAdd?: (index: number) => void;
}

interface RowProps extends React.HTMLAttributes<HTMLTableRowElement> {
    "data-row-key": string;
}

const Row = ({ children, ...props }: RowProps) => {
    const {
        attributes,
        listeners,
        setNodeRef,
        setActivatorNodeRef,
        transform,
        transition,
        isDragging,
        data,
    } = useSortable({
        id: props["data-row-key"],
    });

    // console.log("row props", props);
    // console.log("children", children);
    // console.log("data", data);
    // console.log("data", data.sortable.index);
    // console.log("attributes", attributes);

    const style: React.CSSProperties = {
        ...props.style,
        transform: CSS.Transform.toString(transform && { ...transform, scaleY: 1 }),
        transition,
        ...(isDragging ? { position: "relative", zIndex: 9999 } : {}),
    };

    return (
        <tr {...props} ref={setNodeRef} style={style} {...attributes}>
            {React.Children.map(children, (child) => {
                //
                if ((child as React.ReactElement).key === "sort") {
                    return React.cloneElement(child as React.ReactElement, {
                        children: (
                            <>
                                <MenuOutlined
                                    ref={setActivatorNodeRef}
                                    style={{ touchAction: "none", cursor: "move" }}
                                    {...listeners}
                                />
                                {/* <Space size={"small"}>
                                    <MenuOutlined
                                        ref={setActivatorNodeRef}
                                        style={{ touchAction: "none", cursor: "move" }}
                                        {...listeners}
                                    />
                                    <Button
                                        icon={<PlusOutlined />}
                                        type="text"
                                        onClick={() => {
                                            handleAdd(data.sortable.index);
                                        }}
                                    />
                                    <Button
                                        icon={<DeleteOutlined />}
                                        type="text"
                                        onClick={() => {
                                            handleDelete(data.sortable.index);
                                        }}
                                    />
                                </Space> */}
                            </>
                        ),
                    });
                }
                return child;
            })}
        </tr>
    );
};

const DndTable: React.FC = () => {
    const [dataSource, setDataSource] = useState<DataType[]>([
        {
            key: "id",
            name: "id",
            dataType: "bigint",
            isPk: true,
            isAutoIncrement: true,
            description: "主键自增",
        },
        {
            key: "name",
            name: "name",
            dataType: "varchar(255)",
            description: "姓名",
        },
        {
            key: "age",
            name: "age",
            dataType: "int",
            description: "年龄",
        },
    ]);

    // console.log("dataSource", dataSource);

    const handleAdd = (index: number) => {
        setDataSource((prev: DataType[]) => {
            return [
                ...prev.slice(0, index + 1),
                { key: new Date().getTime().toString() } as DataType,
                ...prev.slice(index + 1),
            ];
        });
        // console.log("handleAdd index", index);
    };

    const handleDelete = (index: number) => {
        setDataSource((prev: DataType[]) => {
            return prev.filter((_v, i) => i !== index);
            // return [
            //     ...prev.slice(0, index + 1),
            //     { key: new Date().getTime().toString() } as DataType,
            //     ...prev.slice(index + 1),
            // ];
        });
        // console.log("handleDelete index", index);
    };

    const columns: ColumnsType<DataType> = [
        {
            // 列头显示文字（函数用法 3.10.0 后支持）
            title: "名称",
            // 列数据在数据项中对应的路径，支持通过数组查询嵌套路径
            dataIndex: "name",
            width: 200,
            render(value: string, _record, index: number) {
                // console.log(value);
                return (
                    <Input
                        style={{ width: 150 }}
                        defaultValue={value}
                        onChange={(value) => {
                            setDataSource((prev: DataType[]) => {
                                // return prev;
                                const next: DataType[] = prev.map((e, i) => {
                                    if (i === index) {
                                        return { ...e, name: value?.target.value } as DataType;
                                    }
                                    return e;
                                });
                                return next;
                            });
                        }}
                    ></Input>
                );
            },
        },
        {
            title: "类型",
            dataIndex: "dataType",
            width: 200,
            render(value: string, _record, index: number) {
                // console.log(value);
                return (
                    <AutoComplete
                        style={{ width: 120 }}
                        defaultValue={value}
                        options={[
                            { value: "varchar(255)" },
                            { value: "varchar(64)" },
                            { value: "int" },
                            { value: "bigint" },
                            { value: "datetime" },
                            { value: "date" },
                            { value: "time" },
                            { value: "bit(1)" },
                        ]}
                        onChange={(value) => {
                            // console.log("onchange", value);
                            setDataSource((prev) => {
                                const next = prev.map((e, i) => {
                                    if (i === index) {
                                        return { ...e, dataType: value };
                                    }
                                    return e;
                                });
                                return next;
                            });
                        }}
                    ></AutoComplete>
                );
            },
        },
        {
            title: "主键",
            dataIndex: "isPk",
            width: 100,
            render(value: boolean | null, _record, index) {
                return (
                    <Checkbox
                        checked={!!value}
                        onChange={(event) => {
                            setDataSource((prev) => {
                                const next = prev.map((e, i) => {
                                    if (i === index) {
                                        return { ...e, isPk: event.target.checked };
                                    }
                                    return e;
                                });
                                return next;
                            });
                        }}
                    />
                );
            },
        },
        {
            title: "自增",
            width: 100,
            dataIndex: "isAutoIncrement",
            render(value: boolean | null, _record, index) {
                return (
                    <Checkbox
                        checked={!!value}
                        onChange={(event) => {
                            setDataSource((prev) => {
                                const next = prev.map((e, i) => {
                                    if (i === index) {
                                        return { ...e, isAutoIncrement: event.target.checked };
                                    }
                                    return e;
                                });
                                return next;
                            });
                        }}
                    />
                );
            },
        },
        {
            title: "注释",
            dataIndex: "description",
            render(value: string, _record, index: number) {
                // console.log(value);
                return (
                    <Input
                        style={{ maxWidth: 350, minWidth: 150 }}
                        defaultValue={value}
                        onChange={(value) => {
                            setDataSource((prev: DataType[]) => {
                                // return prev;
                                const next: DataType[] = prev.map((e, i) => {
                                    if (i === index) {
                                        return {
                                            ...e,
                                            description: value?.target.value,
                                        } as DataType;
                                    }
                                    return e;
                                });
                                return next;
                            });
                        }}
                    ></Input>
                );
            },
        },
        {
            title: "操作",
            dataIndex: "sort",
            onCell: (record: DataType, index?: number): any => ({
                record,
                index,
                handleAdd,
                handleDelete,
            }),
        },
    ];

    const onDragEnd = ({ active, over }: DragEndEvent) => {
        if (active.id !== over?.id) {
            setDataSource((previous) => {
                const activeIndex = previous.findIndex((i) => i.key === active.id);
                const overIndex = previous.findIndex((i) => i.key === over?.id);
                return arrayMove(previous, activeIndex, overIndex);
            });
        }
    };

    /* 输入框 */
    const [form] = Form.useForm<DataType>();

    const sensors = useSensors(
        useSensor(MouseSensor, {
            activationConstraint: {
                distance: 8, // 8px
            },
        }),
    );

    return (
        <>
            <DndContext
                modifiers={[restrictToVerticalAxis]}
                onDragEnd={onDragEnd}
                sensors={sensors}
            >
                <SortableContext
                    // rowKey array
                    items={dataSource.map((i) => i.key)}
                    strategy={verticalListSortingStrategy}
                >
                    <Table
                        components={{
                            body: {
                                row: Row,
                                // 防止单元格重复渲染导致光标无法聚焦
                                cell: useCallback(
                                    ({
                                        children,
                                        index,
                                        handleAdd,
                                        handleDelete,
                                        ...restProps
                                    }: {
                                        children: React.ReactElement;
                                        index: number;
                                        handleDelete: (index: number) => void;
                                        handleAdd: (index: number) => void;
                                    }) => {
                                        return (
                                            <td {...restProps}>
                                                <Space>
                                                    {children}
                                                    {handleAdd && (
                                                        <>
                                                            <Button
                                                                icon={<PlusOutlined />}
                                                                type="text"
                                                                onClick={() => {
                                                                    handleAdd(index);
                                                                }}
                                                            />
                                                            <Button
                                                                icon={<DeleteOutlined />}
                                                                type="text"
                                                                onClick={() => {
                                                                    handleDelete(index);
                                                                }}
                                                            />
                                                        </>
                                                    )}
                                                </Space>
                                            </td>
                                        );
                                    },
                                    [],
                                ),
                            },
                        }}
                        bordered
                        rowKey="key"
                        columns={columns}
                        dataSource={dataSource}
                        pagination={false}
                        size="small"
                    />
                </SortableContext>
            </DndContext>
        </>
    );
};

export default DndTable;
